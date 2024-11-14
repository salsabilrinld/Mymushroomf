package com.example.mymushroomf;

public class Transaction1 {

    private String name;
    private String type;
    private String status;
    private String price;
    private String imageUri;

    public Transaction1(String name, String type, String status, String price, String imageUri) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.price = price;
        this.imageUri = imageUri;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUri() {
        return imageUri;
    }
}