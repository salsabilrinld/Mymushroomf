package com.example.mymushroomf.PembeliModel;

public class Produk1 {
    private String name;
    private String desc;
    private String type;
    private String price;
    private int imageResId;

    public Produk1(String name, String desc, String type, String price, int imageResId) {
        this.name = name;
        this.desc = desc;
        this.type = type;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}