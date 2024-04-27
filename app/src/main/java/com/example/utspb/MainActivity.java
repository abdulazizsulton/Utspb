package com.example.utspb;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.utspb.R;
import com.example.utspb.data.response.SearchGithub;
import com.example.utspb.data.response.UserGithub;
import com.example.utspb.data.retrofit.ApiConfig;
import com.example.utspb.data.retrofit.ApiService;
import com.example.utspb.ui.GithubAdaptor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GithubAdaptor adapter;

    private EditText editTextName;
    private Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        editTextName = findViewById(R.id.edit_text_name);
        buttonSearch = findViewById(R.id.button_search);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = editTextName.getText().toString();

                ApiService apiService = ApiConfig.getApiService();
                Call<SearchGithub> call = apiService.searchUsers(searchQuery);

                call.enqueue(new Callback<SearchGithub>() {
                    @Override
                    public void onResponse(Call<SearchGithub> call, Response<SearchGithub> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            List<UserGithub> users = response.body().getUsers();
                            adapter = new GithubAdaptor(users);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        } else {
                            Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchGithub> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
