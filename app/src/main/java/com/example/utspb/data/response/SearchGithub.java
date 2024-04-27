package com.example.utspb.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchGithub {
    @SerializedName("items")
    private List<UserGithub> users;

    public List<UserGithub> getUsers() {
        return users;
    }


}

