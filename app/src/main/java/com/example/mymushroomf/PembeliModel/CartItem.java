package com.example.mymushroomf.PembeliModel;


public class CartItem {
    private Produk1 product;
    private int quantity;
    private boolean isSelected;

    public CartItem(Produk1 product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.isSelected = true; // Default terpilih
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    // Menambahkan metode getPrice() untuk mengambil harga total produk berdasarkan quantity
    public int getPrice() {
        // Mengambil harga produk dan mengalikannya dengan jumlah
        return product.getPrice() * quantity;
    }
}
