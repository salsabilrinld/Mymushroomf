package com.example.mymushroomf.PembeliModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Produk1 implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("product_name")
    private String product_name;
    @SerializedName("description")
    private String description;
    @SerializedName("category")
    private String category;
    @SerializedName("stock")
    private int stock;
    @SerializedName("price")
    private int price;
    @SerializedName("file_path")
    private String file_path;  // Assuming the image is a String URL or path

    // Constructor
    public Produk1(int id, String product_name, String description, String category, int stock, int price, String file_path) {
        this.id = id;
        this.product_name = product_name;
        this.description = description;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.file_path = file_path;
    }

    public int getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public int getStock() {
        return stock;
    }

    public int getPrice() {
        return price;
    }

    public String getFile_path() {
        return file_path;
    }
}