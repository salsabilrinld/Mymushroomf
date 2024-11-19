package com.example.mymushroomf.PembeliModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;


public class Produk1 implements Serializable {
    private String productId;
    private String name;
    private String desc;
    private String category;
    private String stock;
    private int price;
    private int imageResId;  // Assuming the image is a String URL or path

    // Constructor
    public Produk1(String productId, String name, String desc, String category, String stock, int price, int imageResId) {
        this.productId = productId;
        this.name = name;
        this.desc = desc;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getCategory() {
        return category;
    }

    public String getStock() {
        return stock;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

}