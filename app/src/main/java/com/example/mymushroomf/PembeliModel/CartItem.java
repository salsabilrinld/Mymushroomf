package com.example.mymushroomf.PembeliModel;

public class CartItem {
    private Produk1 product;
    private int quantity;

    public CartItem(Produk1 product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Produk1 getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return product.getName();
    }

    public int getImageResId() {
        return product.getImageResId();
    }

    public String getPrice() {
        return product.getPrice();
    }
}

