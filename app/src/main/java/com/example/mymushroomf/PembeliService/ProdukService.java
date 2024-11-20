package com.example.mymushroomf.PembeliService;

import com.example.mymushroomf.PembeliModel.Produk1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProdukService {
    @GET("product")
    Call<List<Produk1>> getProducts();

    @GET("products/{id}")
    Call<Produk1> getProductDetail(@Path("id") int productId);
}
