package com.example.mymushroomf.PembeliModel;

import java.util.Objects;

public class Produk1 {
    private String name;
    private String desc;
    private String type;
    private int price;          // Harga produk
    private int imageResId;     // ID sumber daya gambar produk

    // Konstruktor untuk Produk1
    public Produk1(String name, String desc, String type, int price, int imageResId) {
        this.name = name;
        this.desc = desc;
        this.type = type;
        this.price = price;
        this.imageResId = imageResId;
    }

    // Getter untuk nama produk
    public String getName() {
        return name;
    }

    // Getter untuk deskripsi produk
    public String getDesc() {
        return desc;
    }

    // Getter untuk tipe produk
    public String getType() {
        return type;
    }

    // Getter untuk harga produk
    public int getPrice() {
        return price;
    }

    // Getter untuk ID gambar produk
    public int getImageResId() {
        return imageResId;
    }

    // Override equals dan hashCode untuk memastikan perbandingan produk yang lebih baik
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produk1 produk1 = (Produk1) o;
        return price == produk1.price &&
                Objects.equals(name, produk1.name) &&
                Objects.equals(desc, produk1.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc, price);
    }
}
