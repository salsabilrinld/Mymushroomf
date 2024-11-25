package com.example.mymushroomf.PembeliService;

import com.example.mymushroomf.PembeliModel.CartItem;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface CartService {
    @POST("add-to-cart")
    Call<CartItem> addToCart(@Header("Authorization") String token, @Body CartItem cartItem);

    @GET("cart")
    Call<List<CartItem>> viewCart(@Header("Authorization") String token);

    @PUT("cart/{cartItemId}")
    Call<CartItem> updateCart(@Header("Authorization") String token, @Path("cartItemId") int cartItemId, @Body CartItem cartItem);

    @DELETE("cart/{cartItemId}")
    Call<Void> removeFromCart(@Header("Authorization") String token, @Path("cartItemId") int cartItemId);

    @DELETE("cart-clear")
    Call<Void> clearCart(@Header("Authorization") String token);
}
