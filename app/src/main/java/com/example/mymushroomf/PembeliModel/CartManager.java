package com.example.mymushroomf.PembeliModel;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static CartManager instance;
    private List<CartItem> cartItems;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    private CartManager(Context context) {
        sharedPreferences = context.getSharedPreferences("Cart", Context.MODE_PRIVATE);
        gson = new Gson();
        loadCart(); // Memuat data keranjang saat CartManager diinisialisasi
    }

    // Mendapatkan instance CartManager (Singleton)
    public static CartManager getInstance(Context context) {
        if (instance == null) {
            instance = new CartManager(context);
        }
        return instance;
    }

    // Menambahkan item ke keranjang
    public void addItem(CartItem item) {
        boolean itemExists = false;

        // Memeriksa apakah item sudah ada di keranjang
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().equals(item.getProduct())) {
                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                itemExists = true;
                break;
            }
        }

        // Jika item tidak ada di keranjang, tambahkan item baru
        if (!itemExists) {
            cartItems.add(item);
        }

        // Simpan kembali keranjang setelah item ditambahkan
        saveCart();
    }

    // Menghapus item dari keranjang
    public void removeItem(CartItem item) {
        cartItems.remove(item);
        saveCart();
    }

    // Mendapatkan semua item dalam keranjang
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    // Menghitung total harga semua item dalam keranjang
    public double getTotalPrice() {
        double totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return totalPrice;
    }

    // Menyimpan data keranjang ke SharedPreferences
    public void saveCart() {
        String json = gson.toJson(cartItems);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("cartItems", json); // Menyimpan data dalam bentuk JSON
        editor.apply(); // Simpan data secara asynchronous
    }

    // Memuat data keranjang dari SharedPreferences
    private void loadCart() {
        String json = sharedPreferences.getString("cartItems", null);
        if (json != null) {
            try {
                Type type = new TypeToken<List<CartItem>>() {}.getType();
                cartItems = gson.fromJson(json, type);
                if (cartItems == null) {
                    cartItems = new ArrayList<>();
                }
            } catch (Exception e) {
                cartItems = new ArrayList<>(); // Jika gagal memuat data, buat list baru
            }
        } else {
            cartItems = new ArrayList<>(); // Jika belum ada data, buat list kosong
        }
    }

    // Memeriksa apakah produk sudah ada di keranjang
    public boolean isProductInCart(Produk product) {
        for (CartItem item : cartItems) {
            if (item.getProduct().equals(product)) {
                return true;
            }
        }
        return false;
    }
}
