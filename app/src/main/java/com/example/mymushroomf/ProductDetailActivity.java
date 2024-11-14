package com.example.mymushroomf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    private RecyclerView reviewRecyclerView;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewList;
    private TextView productNameTextView;
    private TextView productDescriptionTextView;
    private TextView productPriceTextView;
    private ImageView productImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);

        reviewRecyclerView = findViewById(R.id.reviewRecyclerView);
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        reviewList = new ArrayList<>();
        reviewList.add(new Review(4.5f, "https://example.com/image1.jpg", "Produk bagus dan sesuai harapan!"));
        reviewList.add(new Review(5.0f, "https://example.com/image2.jpg", "Sangat puas dengan kualitasnya."));

        reviewAdapter = new ReviewAdapter(this, reviewList);
        reviewRecyclerView.setAdapter(reviewAdapter);

        productImageView = findViewById(R.id.product_image);
        productNameTextView = findViewById(R.id.product_name);
        productDescriptionTextView = findViewById(R.id.product_description);
        productPriceTextView = findViewById(R.id.product_price);

        // Mendapatkan data dari Intent
        Intent intent = getIntent();
        String productName = ((Intent) intent).getStringExtra("productName");
        String productDescription = intent.getStringExtra("productDescription");
        String productPrice = intent.getStringExtra("productPrice");
        int productImageResId = intent.getIntExtra("productImage", R.drawable.jamur_tiram);

        // Mengatur data ke Views
        productNameTextView.setText(productName);
        productDescriptionTextView.setText(productDescription);
        productPriceTextView.setText(productPrice);
        productImageView.setImageResource(productImageResId);

        // Tombol kembali
        ImageButton backButton = findViewById(R.id.back_dashboard);
        backButton.setOnClickListener(view -> finish());

        // Tombol Beli Sekarang
        Button buyNowButton = findViewById(R.id.buy_now_button);
        buyNowButton.setOnClickListener(view -> {
            // Memunculkan Popup saat tombol "Buy Now" ditekan
            new Popup(ProductDetailActivity.this);
        });
    }
}
