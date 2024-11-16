package com.example.mymushroomf.PembeliModel;

import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {

    // Method to fetch reviews for a specific product (this could come from an API or database)
    public List<Review> getReviewsForProduct(String productId) {
        List<Review> reviews = new ArrayList<>();

        // Simulate fetching reviews from a database or API
        reviews.add(new Review("JohnDoe", 4.5f, "Great product!"));
        reviews.add(new Review("JaneSmith", 3.0f, "Decent, but could be better."));

        return reviews;
    }
}

