package com.example.mymushroomf.PembeliActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mymushroomf.R;

import java.text.NumberFormat;
import java.util.Locale;

public class Popup {

    private int quantity = 1; // Kuantitas awal
    private int pricePerItem; // Harga per item
    private int totalPrice;   // Total harga

    private TextView quantityText;
    private TextView priceText;

    // Konstruktor Popup
    public Popup(Context context, String productName, String imageResId, int pricePerItem) {
        this.pricePerItem = pricePerItem;
        this.totalPrice = pricePerItem * quantity;

        // Membuat dialog
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup); // Pastikan Anda memiliki layout popup.xml

        // Inisialisasi elemen dalam dialog
        ImageView productImage = dialog.findViewById(R.id.dialog_product_image);
        TextView productNameText = dialog.findViewById(R.id.dialog_product_name);

        priceText = dialog.findViewById(R.id.dialog_product_price);
        quantityText = dialog.findViewById(R.id.quantity_text);

        ImageButton decreaseQuantityButton = dialog.findViewById(R.id.decrease_quantity_button);
        ImageButton increaseQuantityButton = dialog.findViewById(R.id.increase_quantity_button);
        Button buyNowButton = dialog.findViewById(R.id.buy_now_button);

        // Atur data awal
        productNameText.setText(productName);
        Glide.with(context)
                .load(imageResId)                           // Memuat dari URL gambar
//                .placeholder(R.drawable.placeholder_image) // Placeholder saat loading
//                .error(R.drawable.error_image)             // Gambar error jika gagal memuat
                .into(productImage);
        updateQuantityAndPrice();

        // Tombol untuk mengurangi kuantitas
        decreaseQuantityButton.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                updateQuantityAndPrice();
            }
        });

        // Tombol untuk menambah kuantitas
        increaseQuantityButton.setOnClickListener(v -> {
            quantity++;
            updateQuantityAndPrice();
        });

        // Tombol "Buy Now"
        buyNowButton.setOnClickListener(v -> {
            // Intent ke PemesananDetailActivity
            Intent intent = new Intent(context, PemesananDetailActivity.class);

            // Kirim data produk ke PemesananDetailActivity
            intent.putExtra("productName", productName);
            intent.putExtra("quantity", quantity);
            intent.putExtra("totalPrice", totalPrice);

            context.startActivity(intent);
            dialog.dismiss(); // Tutup dialog
        });

        // Tampilkan dialog
        dialog.show();
    }

    // Perbarui kuantitas dan total harga
    private void updateQuantityAndPrice() {
        if (quantityText != null && priceText != null) {
            totalPrice = pricePerItem * quantity;
            quantityText.setText(String.valueOf(quantity));
            priceText.setText(formatCurrency(totalPrice));
        }
    }

    // Format harga menjadi mata uang Indonesia (Rp)
    private String formatCurrency(int amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return format.format(amount).replace("Rp", "Rp. ").replace(",00", "");
    }
}
