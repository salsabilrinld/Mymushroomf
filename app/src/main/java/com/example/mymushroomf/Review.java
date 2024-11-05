package com.example.mymushroomf;

public class Review {
    private float rating;
    private String imageUrl;
    private String comment;

    public Review(float rating, String imageUrl, String comment) {
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.comment = comment;
    }

    public float getRating() { return rating; }
    public String getImageUrl() { return imageUrl; }
    public String getComment() { return comment; }
}


