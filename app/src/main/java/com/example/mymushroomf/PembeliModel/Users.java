package com.example.mymushroomf.PembeliModel;

import com.google.gson.annotations.SerializedName;

public class Users {
    private String status;
    private UserData user;

    public String getStatus() {
        return status;
    }

    public UserData getUser() {
        return user;
    }

    public static class UserData {
        @SerializedName("id")
        private int id;
        @SerializedName("email")
        private String email;
        @SerializedName("username")
        private String username;

        public int getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getUsername() {
            return username;
        }
    }
}