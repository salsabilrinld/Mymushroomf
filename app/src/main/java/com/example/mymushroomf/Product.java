package  com.example.mymushroomf;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private String id;
    private String name;
    private String desc;
    private String type;
    private String price;
    private String imageResource;

    // Constructor
    public Product(String name, String desc, String price, String imageResource) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.imageResource = imageResource;
    }

    public Product(String id, String name, String desc, String price, String imageResource) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.type = type;
        this.price = price;
        this.imageResource = imageResource;
    }

    // Parcelable constructor
    protected Product(Parcel in) {
        id = in.readString();
        name = in.readString();
        desc = in.readString();
        type = in.readString();
        price = in.readString();
        imageResource = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(desc);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }
}
