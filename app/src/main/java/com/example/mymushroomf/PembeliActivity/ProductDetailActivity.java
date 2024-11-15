package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.Popup;
import com.example.mymushroomf.R;
import com.example.mymushroomf.Review;
import com.example.mymushroomf.ReviewAdapter;
import com.example.mymushroomf.ReviewRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    private RecyclerView reviewRecyclerView;
    private ReviewAdapter reviewAdapter;
    private ReviewRepository reviewRepository;
    private List<Review> reviewList;
    private TextView productNameTextView;
    private TextView productDescriptionTextView;
    private TextView productPriceTextView;
    private ImageView productImageView;
    private String productId = "product_123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);

        reviewRecyclerView = findViewById(R.id.reviewRecyclerView);
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        reviewRepository = new ReviewRepository();
        reviewList = reviewRepository.getReviewsForProduct(productId);

//        reviewList = new ArrayList<>();
//        reviewList.add(new Review("salsabilrinld", 4.5f,  "Produk bagus dan sesuai harapan!"));
//        reviewList.add(new Review("yoasourbi", 5.0f,  "Sangat puas dengan kualitasnya."));

        reviewAdapter = new ReviewAdapter(this, reviewList);
        reviewRecyclerView.setAdapter(reviewAdapter);

        productImageView = findViewById(R.id.product_image);
        productNameTextView = findViewById(R.id.product_name);
        productDescriptionTextView = findViewById(R.id.product_description);
        productPriceTextView = findViewById(R.id.product_price);

        // Mendapatkan data dari Intent
        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");
        String productDescription = intent.getStringExtra("productDescription");
        String productPrice = intent.getStringExtra("productPrice");
        int productImageResId = intent.getIntExtra("productImage", R.drawable.jamur_tiram);

        // Mengatur data ke Views
        productNameTextView.setText(productName);
        productDescriptionTextView.setText(productDescription);
        productPriceTextView.setText(productPrice);
        productImageView.setImageResource(productImageResId);

        ImageButton backButton = findViewById(R.id.back_dashboard);
        backButton.setOnClickListener(view -> finish());

        Button buyNowButton = findViewById(R.id.buy_now_button);
        buyNowButton.setOnClickListener(view -> {
            new Popup(ProductDetailActivity.this);
        });
    }
}
