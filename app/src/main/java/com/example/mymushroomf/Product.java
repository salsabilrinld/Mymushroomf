package  com.example.mymushroomf;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String name;
    private String type;
    private String price;
    private String imageResource;

    // Constructor
    public Product(String name, String description, String price, String imageResource) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.imageResource = imageResource;
    }

    // Parcelable constructor
    protected Product(Parcel in) {
        name = in.readString();
        type = in.readString();
        price = in.readString();
        imageResource = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(price);
        dest.writeString(imageResource);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Creator for Parcelable
    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    // Getters (if needed)
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    public String getImageResId() {
        return imageResource;
    }
}
