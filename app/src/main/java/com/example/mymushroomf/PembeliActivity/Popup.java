package com.example.mymushroomf.PembeliActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymushroomf.R;

public class Popup {

    private int quantity = 1; // Initial quantity
    private int pricePerItem;// Initial price per item
    private int totalPrice; // Initial total price

    private TextView quantityText;
    private TextView priceText;

    // Constructor for Popup class
    public Popup(Context context, String productName, int imageResId, int pricePerItem) {
        this.pricePerItem = pricePerItem;
        this.totalPrice = pricePerItem * quantity;
        // Create and set up the dialog
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup); // Reference to the popup XML layout

        // Initialize dialog views
        ImageView productImage = dialog.findViewById(R.id.dialog_product_image);
        TextView productNameText = dialog.findViewById(R.id.dialog_product_name);

        // Assign to class-level fields
        priceText = dialog.findViewById(R.id.dialog_product_price);
        quantityText = dialog.findViewById(R.id.quantity_text);

        ImageButton decreaseQuantityButton = dialog.findViewById(R.id.decrease_quantity_button);
        ImageButton increaseQuantityButton = dialog.findViewById(R.id.increase_quantity_button);
        Button buyNowButton = dialog.findViewById(R.id.buy_now_button);

        // Set initial data
        productNameText.setText(productName);
        priceText.setText("Rp. " + pricePerItem);// Change as needed
        productImage.setImageResource(imageResId); // Change as needed
        updatePrice(); // Update price based on quantity

        // Set button click listeners for quantity adjustment
        decreaseQuantityButton.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                updateQuantityAndPrice();
            }
        });

        increaseQuantityButton.setOnClickListener(v -> {
            quantity++;
            updateQuantityAndPrice();
        });

        // Handle Buy Now action
        buyNowButton.setOnClickListener(v -> {

            // Navigate to PemesananDetailActivity
            Intent intent = new Intent(context, PemesananDetailActivity.class);

            // Pass data to PemesananDetailActivity
            intent.putExtra("productName", "Jamur Tiram");
            intent.putExtra("quantity", quantity); // Pass updated quantity
            intent.putExtra("totalPrice", totalPrice); // Pass updated total price
            context.startActivity(intent);

            dialog.dismiss(); // Close the dialog after action
        });

        // Show the dialog
        dialog.show();
    }

    private void updateQuantityAndPrice() {
        if (quantityText != null && priceText != null) {
            totalPrice = pricePerItem * quantity; // Calculate the new total price
            quantityText.setText(String.valueOf(quantity)); // Show the updated price in quantityText
            priceText.setText("Rp. " + totalPrice);    // Update the product cost
        }
    }

    private void updatePrice() {
        totalPrice = quantity * pricePerItem; // Calculate total price
        if (priceText != null) {
            priceText.setText("Rp. " + totalPrice); // Set the price in the UI
        }
    }
}
