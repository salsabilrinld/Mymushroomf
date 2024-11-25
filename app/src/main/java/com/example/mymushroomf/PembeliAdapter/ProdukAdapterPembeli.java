package com.example.mymushroomf.PembeliAdapter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mymushroomf.ApiClient;
import com.example.mymushroomf.PembeliActivity.ProductDetailActivity;
import com.example.mymushroomf.PembeliModel.CartItem;
import com.example.mymushroomf.PembeliModel.Produk;
import com.example.mymushroomf.PembeliService.CartService;
import com.example.mymushroomf.R;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProdukAdapterPembeli extends RecyclerView.Adapter<ProdukAdapterPembeli.ProductViewHolder> {

    private List<Produk> productList;
    private Context context;
    private CartItem cartItem;

    public ProdukAdapterPembeli(Context context, List<Produk> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout for each product item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.produk1, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Produk product = productList.get(position);

        Log.d("Adapter", "Binding product: " + product.getProduct_name());
        // Validasi null untuk data produk
        if (product != null) {
            holder.productName.setText(product.getProduct_name());
            holder.productCategory.setText(product.getCategory());
            holder.productPrice.setText(formatCurrency(product.getPrice()));

            // Memuat gambar produk menggunakan Glide
            Glide.with(holder.itemView.getContext())
                    .load("http://192.168.21.116/storage/" + product.getFile_path()) // Pastikan base URL benar
//                    .placeholder(R.drawable.placeholder_image) // Placeholder jika gambar sedang dimuat
//                    .error(R.drawable.error_image) // Placeholder jika gambar gagal dimuat
                    .into(holder.productImage);

            // Aksi klik item untuk membuka detail produk
            holder.itemView.setOnClickListener(view -> {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("product_id", product.getId());
                context.startActivity(intent);
            });

            // Aksi klik tombol tambah ke keranjang
            holder.addToCartButton.setOnClickListener(view -> {
                    addToCart(product);
            });
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // Metode untuk memperbarui data produk dan menyegarkan tampilan
    public void updateProductList(List<Produk> newProductList) {
        this.productList.clear();
        this.productList.addAll(newProductList);
        notifyDataSetChanged();
    }


    private void addToCart(Produk product) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        if (token.isEmpty()) {
            Toast.makeText(context, "User not authenticated. Please log in.", Toast.LENGTH_SHORT).show();
            return;
        }

        CartItem cartItem = new CartItem(product);
        Log.d("AddToCart", "CartItem details: " + new Gson().toJson(cartItem));


        CartService cartService = ApiClient.getCartService();
        Call<CartItem> call = cartService.addToCart("Bearer " + token, cartItem);

        call.enqueue(new Callback<CartItem>() {
            @Override
            public void onResponse(Call<CartItem> call, Response<CartItem> response) {
                Log.d("AddToCart", "Response code: " + response.code());
                Log.d("AddToCart", "Response body: " + new Gson().toJson(response.body()));
                Log.d("AddToCart", "Response error body: " + (response.errorBody() != null ? response.errorBody().toString() : "No error body"));

                if (response.isSuccessful()) {
                    // Handle successful response
                    Log.d("AddToCart", "Response: " + new Gson().toJson(response.body()));
                    Toast.makeText(context, "Product added to cart", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle failure
                    Toast.makeText(context, "Failed to add product to cart", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartItem> call, Throwable t) {
                Toast.makeText(context, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productCategory, productPrice;
        ImageButton addToCartButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productCategory = itemView.findViewById(R.id.product_category);
            productPrice = itemView.findViewById(R.id.product_price);
            addToCartButton = itemView.findViewById(R.id.addtocart_button);
        }
    }

    private String formatCurrency(int amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return format.format(amount).replace("Rp", "Rp. ").replace(",00", "");
    }
}
