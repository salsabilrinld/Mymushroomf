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
        @SerializedName("password")
        private String password;
        @SerializedName("username")
        private String username;
        @SerializedName("phone")
        private String phone;
        @SerializedName("profile_path")
        private String profilePath;

        public int getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getUsername() {
            return username;
        }

        public String getPhone() {
            return phone;
        }

        public String getProfilePath() {
            return profilePath;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setProfilePath(String profilePath) {
            this.profilePath = profilePath;
        }
    }
}