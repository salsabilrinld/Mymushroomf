package com.example.mymushroomf.PembeliActivity;

import android.content.Context;
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

import com.example.mymushroomf.PembeliModel.CartItem;
import com.example.mymushroomf.PembeliModel.Produk1;
import com.example.mymushroomf.R;
import com.example.mymushroomf.PembeliModel.Review;
import com.example.mymushroomf.PembeliAdapter.ReviewAdapter;
import com.example.mymushroomf.PembeliModel.ReviewRepository;

import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    private RecyclerView reviewRecyclerView;
    private Context context;
    private ReviewAdapter reviewAdapter;
    private ReviewRepository reviewRepository;
    private List<Review> reviewList;
    private List<Produk1> productList;
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
            int price = Integer.parseInt(productPrice.replace("Rp.", "").replace(".", "").trim());
            new Popup(ProductDetailActivity.this, productName, productImageResId, price);
        });

        Produk1 product = new Produk1("Jamur Tiram", "Lorem Ipsum", "Organik", "Rp. 12.000", R.drawable.jamur_tiram);

        Button addToCartButton = findViewById(R.id.add_to_cart_button);
        addToCartButton.setOnClickListener(view -> {
            // Check if the product has stock available
            if (product.getStock() > 0) {
                // Add to cart
                CartItem cartItem = new CartItem(product, 1);  // Adding 1 unit to the cart
                CartActivity cartActivity = (CartActivity) getApplicationContext();
                cartActivity.addItemToCart(cartItem);

                // Decrease the product stock
                product.setStock(product.getStock() - 1);
            } else {
                // Show message: Out of stock
                Toast.makeText(ProductDetailActivity.this, "Out of stock!", Toast.LENGTH_SHORT).show();
            }
    }
}
