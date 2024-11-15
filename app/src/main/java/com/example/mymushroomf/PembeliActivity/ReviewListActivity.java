package com.example.mymushroomf.PembeliActivity;


import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.R;
import com.example.mymushroomf.Review;
import com.example.mymushroomf.ReviewAdapter;
import com.example.mymushroomf.ReviewRepository;

import java.util.List;

public class ReviewListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewList;
    private ReviewRepository reviewRepository;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        recyclerView = findViewById(R.id.recycler_viewrating);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reviewRepository = new ReviewRepository();
        reviewList = reviewRepository.getReviewsForProduct("some_product_id");

        reviewAdapter = new ReviewAdapter(this, reviewList);
        recyclerView.setAdapter(reviewAdapter);

        ImageButton backButton = findViewById(R.id.ib_back);
        backButton.setOnClickListener(view -> finish());

    }
}
