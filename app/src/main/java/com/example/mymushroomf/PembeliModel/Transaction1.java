package com.example.mymushroomf.PembeliModel;

public class Transaction1 {

    private String id;
    private String name;
    private String type;
    private String status;
    private String price;
    private String imageUri;

    public Transaction1(String id, String name, String type, String status, String price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.price = price;
        this.imageUri = imageUri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}