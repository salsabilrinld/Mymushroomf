package com.example.mymushroomf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<Review> reviewList;
    private Context context;

    public ReviewAdapter(Context context, List<Review> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviewList.get(position);

        holder.ratingBar.setRating(review.getRating());
        holder.reviewComment.setText(review.getComment());

        // Load image using Glide or any image loading library
        Glide.with(context).load(review.getImageUrl()).into(holder.reviewImage);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        RatingBar ratingBar;
        ImageView reviewImage;
        TextView reviewComment;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            reviewImage = itemView.findViewById(R.id.reviewImage);
            reviewComment = itemView.findViewById(R.id.reviewComment);
        }
    }
}

