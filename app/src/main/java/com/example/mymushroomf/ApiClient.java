package com.example.mymushroomf;

import com.example.mymushroomf.PembeliService.CartService;
import com.example.mymushroomf.PembeliService.ProdukService;
import com.example.mymushroomf.PembeliService.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://192.168.101.85/MushRoom/public/api/";

    // Method untuk mendapatkan Retrofit instance
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            // Logging Interceptor untuk memantau request dan response
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    // Method untuk mendapatkan ProdukService
    public static ProdukService getProdukService() {
        return getRetrofitInstance().create(ProdukService.class);
    }

    // Method untuk mendapatkan UserService
    public static UserService getUserService() {
        return getRetrofitInstance().create(UserService.class);
    }

    public static CartService getCartService() {
        return getRetrofitInstance().create(CartService.class);
    }
}
