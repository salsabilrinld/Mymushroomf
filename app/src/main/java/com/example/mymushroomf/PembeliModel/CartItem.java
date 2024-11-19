package com.example.mymushroomf.PembeliModel;

import java.io.Serializable;

public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L; // Menambahkan serialVersionUID untuk identifikasi versi kelas

    private Produk1 product;
    private int quantity;
    private boolean isSelected;

    // Constructor
    public CartItem(Produk1 product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.isSelected = false;
    }

    // Getter methods for CartItem fields
    public Produk1 getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isSelected() {
        return isSelected;
    }

    // Setter methods for CartItem fields
    public void setProduct(Produk1 product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
