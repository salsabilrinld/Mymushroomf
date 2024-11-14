package com.example.mymushroomf;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Popup {

    private int quantity = 1; // initial quantity
    private int pricePerItem = 12000; // initial price per item (can be dynamic)
    private TextView quantityText;
    private TextView priceText;

    // Constructor for Popup class
    public Popup(Context context) {
        // Create and set up the dialog
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popup); // Reference to the popup XML layout

        // Initialize dialog views
        ImageView productImage = dialog.findViewById(R.id.dialog_product_image);
        TextView productName = dialog.findViewById(R.id.dialog_product_name);
        priceText = dialog.findViewById(R.id.dialog_product_price);
        quantityText = dialog.findViewById(R.id.quantity_text);
        ImageButton decreaseQuantityButton = dialog.findViewById(R.id.decrease_quantity_button);
        ImageButton increaseQuantityButton = dialog.findViewById(R.id.increase_quantity_button);
        Button buyNowButton = dialog.findViewById(R.id.buy_now_button);

        // Set initial data
        productName.setText("Jamur Tiram"); // Change as needed
        productImage.setImageResource(R.drawable.jamur_tiram); // Change as needed
        updatePrice(); // Update price based on quantity

        // Set button click listeners for quantity adjustment
        decreaseQuantityButton.setOnClickListener(v -> decreaseQuantity());
        increaseQuantityButton.setOnClickListener(v -> increaseQuantity());

        // Handle Buy Now action
        buyNowButton.setOnClickListener(v -> {
            // Navigate to OrderDetailActivity
            Intent intent = new Intent(context, OrderDetailActivity.class);
            // Optional: Pass data to OrderDetailActivity
            intent.putExtra("productName", "Jamur Tiram");
            intent.putExtra("quantity", quantity);
            intent.putExtra("totalPrice", quantity * pricePerItem);
            context.startActivity(intent);

            dialog.dismiss(); // Close the dialog after action
        });

        // Show the dialog
        dialog.show();
    }

    private void decreaseQuantity() {
        if (quantity > 1) {
            quantity--;
            updateQuantityAndPrice();
        }
    }

    private void increaseQuantity() {
        quantity++;
        updateQuantityAndPrice();
    }

    private void updateQuantityAndPrice() {
        quantityText.setText(String.valueOf(quantity)); // Update the quantity in the UI
        updatePrice(); // Update price
    }

    private void updatePrice() {
        int totalPrice = quantity * pricePerItem; // Calculate total price
        priceText.setText("Rp. " + totalPrice); // Set the price in the UI
    }
}
