package com.example.mymushroomf;

public class Produk1 {
    private String name;
    private String weight;
    private String price;
    private int imageResId;

    public Produk1(String name, String weight, String price, int imageResId) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public String getWeight() { return weight; }
    public String getPrice() { return price; }
    public int getImageResId() { return imageResId; }
}
