package com.example.mymushroomf.PembeliModel;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;


public class Produk1 implements Parcelable {
    private String name;
    private String desc;
    private String type;
    private int price;
    private int imageResId;  // Assuming the image is a String URL or path

    // Constructor
    public Produk1(String name, String desc, String type, int price, int imageResId) {
        this.name = name;
        this.desc = desc;
        this.type = type;
        this.price = price;
        this.imageResId = imageResId;
    }

    // Parcelable constructor
    protected Produk1(Parcel in) {
        name = in.readString();
        desc = in.readString();
        type = in.readString();
        price = in.readInt();
        imageResId = in.readInt();
    }

    // Creator object to create an instance of Produk1 from a Parcel
    public static final Creator<Produk1> CREATOR = new Creator<Produk1>() {
        @Override
        public Produk1 createFromParcel(Parcel in) {
            return new Produk1(in);
        }

        @Override
        public Produk1[] newArray(int size) {
            return new Produk1[size];
        }
    };

    // Getter methods for all fields
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    // Method to write the object data to Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(desc);
        dest.writeString(type);
        dest.writeInt(price);
        dest.writeInt(imageResId);  // Write image path/URL as String
    }

    @Override
    public int describeContents() {
        return 0;
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
