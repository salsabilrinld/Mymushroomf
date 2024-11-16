package com.example.mymushroomf.PembeliAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.PembeliModel.Review;
import com.example.mymushroomf.R;

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

        holder.reviewUsername.setText(review.getUsername());
        holder.ratingBar.setRating(review.getRating());
        holder.reviewComment.setText(review.getComment());

    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView reviewUsername;
        RatingBar ratingBar;
        TextView reviewComment;

        public ReviewViewHolder(View itemView) {
            super(itemView);
            reviewUsername = itemView.findViewById(R.id.review_username);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            reviewComment = itemView.findViewById(R.id.review_comment);

        }
    }
}

