package com.example.mymushroomf;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Keranjang1Activity extends AppCompatActivity {

    private LinearLayout itemLayout, itemLayout2;
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

        // Handle increase quantity for item 1
        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(itemQuantity.getText().toString());
                itemQuantity.setText(String.valueOf(quantity + 1));
            }
        });

        // Handle decrease quantity for item 1
        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(itemQuantity.getText().toString());
                if (quantity > 1) {
                    itemQuantity.setText(String.valueOf(quantity - 1));
                }
            }
        });

        // Handle increase quantity for item 2
        btnIncrease2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(itemQuantity2.getText().toString());
                itemQuantity2.setText(String.valueOf(quantity + 1));
            }
        });

        // Handle decrease quantity for item 2
        btnDecrease2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(itemQuantity2.getText().toString());
                if (quantity > 1) {
                    itemQuantity2.setText(String.valueOf(quantity - 1));
                }
            }
        });

        // Handle delete item for item 1
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemLayout.setVisibility(View.GONE); // Hide the item layout
                Toast.makeText(Keranjang1Activity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle delete item for item 2
        btnDelete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemLayout2.setVisibility(View.GONE); // Hide the item layout
                Toast.makeText(Keranjang1Activity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}