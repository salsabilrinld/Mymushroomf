package com.example.mymushroomf;

import com.example.mymushroomf.services.ProdukService;
import com.example.mymushroomf.PembeliService.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://mushroom.miauwlan.com/api/";

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static ProdukService getProdukService() {
        return getRetrofitInstance().create(ProdukService.class);
    }

    public static UserService getUserData() {
        return getRetrofitInstance().create(UserService.class);
    }
}