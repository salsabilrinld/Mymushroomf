package com.example.mymushroomf.PembeliService;

import com.example.mymushroomf.PembeliModel.Produk;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProdukService {
    @GET("product")
    Call<List<Produk>> getProducts();

    @GET("products/{id}")
    Call<Produk> getProductDetail(@Path("id") int productId);
}
