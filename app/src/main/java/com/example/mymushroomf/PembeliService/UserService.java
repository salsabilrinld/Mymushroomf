package com.example.mymushroomf.PembeliService;

import com.example.mymushroomf.PembeliModel.Users;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

    public interface UserService {
        @GET("login")
        Call<Users> signin(@Query("email") String email, @Query("password") String password);
    }

