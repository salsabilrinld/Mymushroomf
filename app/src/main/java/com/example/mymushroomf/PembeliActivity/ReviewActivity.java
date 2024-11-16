// ReviewActivity.java
package com.example.mymushroomf.PembeliActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.R;
import com.example.mymushroomf.PembeliModel.Review;
import com.example.mymushroomf.PembeliAdapter.ReviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);

        recyclerView = findViewById(R.id.reviewRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load sample reviews
        loadReviews();

        // Set up the adapter
        reviewAdapter = new ReviewAdapter(this, reviewList);
        recyclerView.setAdapter(reviewAdapter);
    }

    private void loadReviews() {
        reviewList = new ArrayList<>();
        reviewList.add(new Review("Alice", 4.5f, "Great product!"));
        reviewList.add(new Review("Bob", 3.0f, "It’s okay, could be better."));
        reviewList.add(new Review("Charlie", 5.0f, "Absolutely loved it! Highly recommended."));
        // Add more reviews as needed
    }
}
