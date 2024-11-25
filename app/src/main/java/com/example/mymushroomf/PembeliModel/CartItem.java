package com.example.mymushroomf.PembeliModel;

import java.io.Serializable;

public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L; // Menambahkan serialVersionUID untuk identifikasi versi kelas

    private int product_id;
    private int id;
    private Produk product;
    private int quantity;
    private int isSelected;


    // Constructor
    public CartItem(int id, Produk product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.isSelected = 0;

    }


    public Produk getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getId() {
        return id;
    }

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected == 1;
    }

    public void toggleSelection() {
        isSelected = (isSelected == 0) ? 1 : 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct(Produk product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartItem(Produk product) {
        this.product = product;
        this.product_id = product.getId();  // Assuming product has an ID
        this.quantity = 1;  // Default quantity
    }

}
