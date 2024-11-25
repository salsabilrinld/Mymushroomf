package com.example.mymushroomf.PembeliActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mymushroomf.ApiClient;
import com.example.mymushroomf.PembeliModel.CartItem;
import com.example.mymushroomf.PembeliModel.Produk;
import com.example.mymushroomf.PembeliService.CartService;
import com.example.mymushroomf.PembeliService.ProdukService;
import com.example.mymushroomf.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImageView;
    private TextView productNameTextView;
    private TextView productPriceTextView;
    private TextView productStockTextView;
    private TextView productDescriptionTextView;
    private Produk product; // Objek Produk1 untuk data produk
    private Button addToCartButton;
    private Button buyNowButton;
    private ImageButton backButton;
    private CartItem cartItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail); // Pastikan layoutnya benar

        // Initialize views
        productImageView = findViewById(R.id.product_image);
        productNameTextView = findViewById(R.id.product_name);
        productPriceTextView = findViewById(R.id.product_price);
        productStockTextView = findViewById(R.id.product_stock);
        productDescriptionTextView = findViewById(R.id.product_description);
        backButton = findViewById(R.id.back_dashboard);
        buyNowButton = findViewById(R.id.buy_now_button);
        addToCartButton = findViewById(R.id.add_to_cart_button);

        // Dapatkan ID produk dari Intent
        int productId = getIntent().getIntExtra("product_id", -1);
        if (productId != -1) {
            fetchProductDetails(productId);
        }
//        // Pastikan data produk ada
//        if (name != null && description != null && price != 0 && imageResId != 0) {
//            // Buat objek Produk1
//            product = new Produk1("id", name, description, type != null ? type : "General", "stock", price, imageResId);
//
//            // Set data produk ke UI
//            productNameTextView.setText(product.getName());
//            productDescriptionTextView.setText(product.getDesc());
//            productPriceTextView.setText("Rp. " + product.getPrice());
//            productImageView.setImageResource(product.getImageResId());
//        } else {
//            Toast.makeText(this, "Data produk tidak ditemukan!", Toast.LENGTH_SHORT).show();
//            finish();
//            return; // Jika produk tidak ditemukan, hentikan eksekusi
//        }

        // Tombol Kembali
        backButton.setOnClickListener(view -> finish());

        // Tombol Beli Sekarang
        buyNowButton.setOnClickListener(view -> {
            // Tampilkan popup pembelian dengan nama, gambar URL, dan harga produk
            new Popup(this, product.getProduct_name(), product.getFile_path(), product.getPrice()); // Pastikan product.getImageUrl() adalah URL gambar
        });

        // Tombol Tambah ke Keranjang
        addToCartButton.setOnClickListener(view -> {
            addToCart(cartItem.getProduct());
        });

    }

    private void addToCart(Produk product) {
        SharedPreferences sharedPreferences = ProductDetailActivity.this.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        if (token.isEmpty()) {
            Toast.makeText(ProductDetailActivity.this, "User not authenticated. Please log in.", Toast.LENGTH_SHORT).show();
            return;
        }

        CartItem cartItem = new CartItem(product.getId(), product,1);
        CartService cartService = ApiClient.getCartService();

        Call<CartItem> call = cartService.addToCart("Bearer " + token, cartItem);

        call.enqueue(new Callback<CartItem>() {
            @Override
            public void onResponse(Call<CartItem> call, Response<CartItem> response) {
                if (response.isSuccessful()) {
                    CartItem addedCartItem = response.body();
                    // Handle successful response
                    Toast.makeText(ProductDetailActivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle failure
                    Toast.makeText(ProductDetailActivity.this, "Failed to add product to cart", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartItem> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void fetchProductDetails(int productId) {
        ProdukService apiService = ApiClient.getProdukService();
        Call<Produk> call = apiService.getProductDetail(productId);

        call.enqueue(new Callback<Produk>() {
            @Override
            public void onResponse(Call<Produk> call, Response<Produk> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Produk product = response.body();
                    productNameTextView.setText(product.getProduct_name());
                    productPriceTextView.setText("Rp" + product.getPrice());
                    productDescriptionTextView.setText(product.getDescription());
                    productStockTextView.setText("Stock: " + product.getStock());

                    Glide.with(ProductDetailActivity.this)
                            .load(product.getFile_path())
                            .into(productImageView);
                }
            }

            @Override
            public void onFailure(Call<Produk> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, "Error loading product details", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
