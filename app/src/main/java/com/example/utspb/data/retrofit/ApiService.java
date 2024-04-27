package com.example.utspb.data.retrofit;



import com.example.utspb.data.response.SearchGithub;
import com.example.utspb.data.response.UserGithub;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    //    @Headers({"Authorization: token <token>"})
    @GET("search/users")
    Call<SearchGithub> searchUsers(@Query("q") String query);

    //    @Headers({"Token"})
    @GET("users/{username}")
    Call<UserGithub> getUser(@Path("username") String username);

}

