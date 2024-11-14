package com.example.mymushroomf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.R;
import com.example.mymushroomf.Review;
import com.example.mymushroomf.ReviewAdapter;

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
    private Button buyNowButton;
    private Button addToCartButton;

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
        buyNowButton = findViewById(R.id.buy_now_button);
        addToCartButton = findViewById(R.id.add_to_cart_button);

            // Mendapatkan data dari Intent
        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");
        String productDescription = intent.getStringExtra("productDescription");
        String productPrice = intent.getStringExtra("productPrice");
        int productImageResId = intent.getIntExtra("productImage", R.drawable.jamur_tiram);

        // Check if required data is present
        if (productName != null && productDescription != null && productPrice != null) {
            // Set data to views
            productNameTextView.setText(productName);
            productDescriptionTextView.setText(productDescription);
            productPriceTextView.setText(productPrice);
            productImageView.setImageResource(productImageResId);
        } else {
            Toast.makeText(this, "Product details are missing", Toast.LENGTH_SHORT).show();
            finish(); // Close activity if data is missing
        }


        // Buy Now button click listener
        buyNowButton.setOnClickListener(v -> {
            new Popup(this);
            Intent cartIntent = new Intent(ProductDetailActivity.this, Keranjang1Activity.class);
            cartIntent.putExtra("productName", productName);
            cartIntent.putExtra("productPrice", productPrice);
            cartIntent.putExtra("productImage", productImageResId);
            startActivity(cartIntent);
        });

        // Add to Cart button click listener
        addToCartButton.setOnClickListener(v -> {
            // Show a message or update cart
            Toast.makeText(ProductDetailActivity.this, "Produk ditambahkan ke keranjang", Toast.LENGTH_SHORT).show();
        });
    }

    // Method to add sample reviews to review list
    private void loadSampleReviews() {
        reviewList.add(new Review(4.5f, "https://example.com/image1.jpg", "Produk bagus dan sesuai harapan!"));
        reviewList.add(new Review(5.0f, "https://example.com/image2.jpg", "Sangat puas dengan kualitasnya."));
    }
}
