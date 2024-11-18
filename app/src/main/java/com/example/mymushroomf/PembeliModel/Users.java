package com.example.mymushroomf.PembeliModel;

import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    private String id;
    private String username;

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

