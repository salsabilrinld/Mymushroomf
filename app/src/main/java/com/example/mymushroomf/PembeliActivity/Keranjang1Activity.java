package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymushroomf.R;

public class Keranjang1Activity extends AppCompatActivity {

    private LinearLayout itemLayout;
    private LinearLayout itemLayout2;
    private TextView itemName, itemName2;
    private CheckBox checkBox, checkBox2;
    private TextView itemQuantity, itemQuantity2;
    private ImageButton btnIncrease, btnDecrease, btnIncrease2, btnDecrease2;
    private ImageButton btnDelete, btnDelete2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang1); // Ensure the correct XML layout is used

        // Initializing views
        itemLayout = findViewById(R.id.itemLayout);
        itemLayout2 = findViewById(R.id.itemLayout2);
        itemName = findViewById(R.id.itemName);
        itemName2 = findViewById(R.id.itemName2);
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        itemQuantity = findViewById(R.id.itemQuantity);
        itemQuantity2 = findViewById(R.id.itemQuantity2);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnDecrease = findViewById(R.id.btnDecrease);
        btnIncrease2 = findViewById(R.id.btnIncrease2);
        btnDecrease2 = findViewById(R.id.btnDecrease2);
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete2 = findViewById(R.id.btnDelete2);

        // Get data from the intent (product name, price, image)
        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");
        String productPrice = intent.getStringExtra("productPrice");
        int productImageResId = intent.getIntExtra("productImage", R.drawable.jamur_tiram);

        String productName2 = intent.getStringExtra("productName2");
        String productPrice2 = intent.getStringExtra("productPrice2");
        int productImageResId2 = intent.getIntExtra("productImage2", R.drawable.jamur_tiram);

        // Set product information for the first item
        itemName.setText(productName);
        itemQuantity.setText("1");

        // Set product information for the second item
        itemName2.setText(productName2);
        itemQuantity2.setText("1");

        // Handle click on itemLayout (First product)
        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the action when the first product layout is clicked
                String itemNameText = itemName.getText().toString();
                Toast.makeText(Keranjang1Activity.this, "Clicked on: " + itemNameText, Toast.LENGTH_SHORT).show();
            }
        });

        // Handle click on itemLayout2 (Second product)
        itemLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the action when the second product layout is clicked
                String itemNameText = itemName2.getText().toString();
                Toast.makeText(Keranjang1Activity.this, "Clicked on: " + itemNameText, Toast.LENGTH_SHORT).show();
            }
        });

        // Handle increase/decrease quantity for the first item
        setQuantityChangeListeners(itemQuantity, btnIncrease, btnDecrease);

        // Handle increase/decrease quantity for the second item
        setQuantityChangeListeners(itemQuantity2, btnIncrease2, btnDecrease2);

        // Handle delete item for the first item
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemLayout.setVisibility(View.GONE); // Hide the item layout
                Toast.makeText(Keranjang1Activity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle delete item for the second item
        btnDelete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemLayout2.setVisibility(View.GONE); // Hide the item layout
                Toast.makeText(Keranjang1Activity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Helper method to set quantity change listeners for both products
    private void setQuantityChangeListeners(final TextView quantityView, final ImageButton increaseBtn, final ImageButton decreaseBtn) {
        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(quantityView.getText().toString());
                quantityView.setText(String.valueOf(quantity + 1));
            }
        });

        decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(quantityView.getText().toString());
                if (quantity > 1) {
                    quantityView.setText(String.valueOf(quantity - 1));
                }
            }
        });
    }
}
