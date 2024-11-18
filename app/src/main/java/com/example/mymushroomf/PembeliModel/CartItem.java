package com.example.mymushroomf.PembeliModel;
import android.os.Parcel;
import android.os.Parcelable;

public class CartItem implements Parcelable {
    private Produk1 product;
    private int quantity;
    private boolean isSelected;

    // Constructor
    public CartItem(Produk1 product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.isSelected = false;
    }

    // Parcelable constructor
    protected CartItem(Parcel in) {
        product = in.readParcelable(Produk1.class.getClassLoader());
        quantity = in.readInt();
        isSelected = in.readByte() != 0;
    }

    // Creator object for CartItem
    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

    // Getter methods for CartItem fields
    public Produk1 getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // Write CartItem to Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(product, flags);  // Write product (Produk1)
        dest.writeInt(quantity);  // Write quantity
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void setProduct(Produk1 product) {
        this.product = product;
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
}
