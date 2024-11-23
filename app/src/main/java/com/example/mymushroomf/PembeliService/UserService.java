package com.example.mymushroomf.PembeliService;

import com.example.mymushroomf.PembeliModel.Users;
import com.example.mymushroomf.RegisterResponse;
import com.example.mymushroomf.VerifyResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserService {
    @FormUrlEncoded
    @POST("login-app")
    Call<Users> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("register-app")
    Call<RegisterResponse> registerUser(
            @Field("username") String username,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("verify")
    Call<VerifyResponse> verifyCode(
            @Field("email") String email,
            @Field("verification_code") String verificationCode
    );

    @PUT("edit-profile")
    Call<Users> updateProfile(
            @Body Users.Data userData
    );

}

