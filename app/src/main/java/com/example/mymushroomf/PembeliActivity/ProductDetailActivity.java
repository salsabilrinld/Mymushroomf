package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymushroomf.PembeliModel.CartItem;
import com.example.mymushroomf.PembeliModel.CartManager;
import com.example.mymushroomf.PembeliModel.Produk1;
import com.example.mymushroomf.R;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView productImageView;
    private TextView productNameTextView;
    private TextView productPriceTextView;
    private TextView productDescriptionTextView;
    private Produk1 product; // Objek Produk1 untuk data produk
    private Button addToCartButton;
    private Button buyNowButton;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail); // Pastikan layoutnya benar

        // Initialize views
        productImageView = findViewById(R.id.product_image);
        productNameTextView = findViewById(R.id.product_name);
        productPriceTextView = findViewById(R.id.product_price);
        productDescriptionTextView = findViewById(R.id.product_description);
        backButton = findViewById(R.id.back_dashboard);
        buyNowButton = findViewById(R.id.buy_now_button);
        addToCartButton = findViewById(R.id.add_to_cart_button);

        // Ambil Data Produk dari Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("productName");
        String description = intent.getStringExtra("productDescription");
        int price = intent.getIntExtra("productPrice", 0);
        int imageResId = intent.getIntExtra("productImage", 0);
        String type = intent.getStringExtra("productType"); // Tambahkan type jika diperlukan

        // Pastikan data produk ada
        if (name != null && description != null && price != 0 && imageResId != 0) {
            // Buat objek Produk1
            product = new Produk1(name, description, type != null ? type : "General", price, imageResId);

            // Set data produk ke UI
            productNameTextView.setText(product.getName());
            productDescriptionTextView.setText(product.getDesc());
            productPriceTextView.setText("Rp. " + product.getPrice());
            productImageView.setImageResource(product.getImageResId());
        } else {
            Toast.makeText(this, "Data produk tidak ditemukan!", Toast.LENGTH_SHORT).show();
            finish();
            return; // Jika produk tidak ditemukan, hentikan eksekusi
        }

        // Tombol Kembali
        backButton.setOnClickListener(view -> finish());

        // Tombol Beli Sekarang
        buyNowButton.setOnClickListener(view -> {
            // Lakukan pembelian langsung tanpa cek stok
            new Popup(this, product.getName(), product.getImageResId(), product.getPrice()); // Menampilkan popup
        });

        // Tombol Tambah ke Keranjang
        addToCartButton.setOnClickListener(view -> {
            // Membuat CartItem menggunakan data produk
            CartItem cartItem = new CartItem(product, 1); // Tambahkan produk ke keranjang

            // Menambahkan item ke keranjang melalui CartManager
            CartManager.getInstance(this).addItem(cartItem);

            // Beri notifikasi kepada user
            Toast.makeText(this, "Produk ditambahkan ke keranjang", Toast.LENGTH_SHORT).show();

            // Navigasi ke halaman Keranjang setelah produk ditambahkan
            Intent intentToCart = new Intent(this, Keranjang1Activity.class);
            startActivity(intentToCart);
        });

    }

    // Fungsi untuk menambah item ke keranjang menggunakan CartManager
    private void addToCart(CartItem cartItem) {
        // Tambahkan produk ke keranjang
        CartManager.getInstance(this).addItem(cartItem);

        // Tampilkan pesan dan pindah ke Activity Keranjang
        Toast.makeText(this, "Produk berhasil ditambahkan ke keranjang", Toast.LENGTH_SHORT).show();
        Intent intentToCart = new Intent(this, Keranjang1Activity.class);
        startActivity(intentToCart);
    }
}
