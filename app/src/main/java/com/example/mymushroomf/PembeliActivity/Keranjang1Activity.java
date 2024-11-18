package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.PembeliAdapter.CartAdapter;
import com.example.mymushroomf.PembeliModel.CartItem;
import com.example.mymushroomf.PembeliModel.CartManager;
import com.example.mymushroomf.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.PembeliAdapter.CartAdapter;
import com.example.mymushroomf.PembeliModel.CartItem;
import com.example.mymushroomf.PembeliModel.CartManager;
import com.example.mymushroomf.R;

import java.util.ArrayList;
import java.util.List;

public class Keranjang1Activity extends AppCompatActivity implements CartAdapter.OnCartItemInteractionListener {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItems;
    private TextView totalPriceTextView;
    private CheckBox checkBoxSelectAll;
    private Button buttonBeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang1);

        // Initialize views
        cartRecyclerView = findViewById(R.id.recycler_viewkeranjang);
        totalPriceTextView = findViewById(R.id.totalPrice);
        checkBoxSelectAll = findViewById(R.id.checkBoxSelectAll);
        buttonBeli = findViewById(R.id.btnBuy);

        // Set up RecyclerView
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load cart items from CartManager
        cartItems = CartManager.getInstance(this).getCartItems();

        // Set adapter for RecyclerView
        cartAdapter = new CartAdapter(cartItems, this, this);
        cartRecyclerView.setAdapter(cartAdapter);

        // Setup "Select All" checkbox
        checkBoxSelectAll.setOnClickListener(v -> {
            boolean isChecked = checkBoxSelectAll.isChecked();
            selectAllItems(isChecked);
            cartAdapter.notifyDataSetChanged();
            updateTotalPrice();
        });

        // Update total price
        updateTotalPrice();

        // Handle back button
        ImageView backButton = findViewById(R.id.iv_back);
        backButton.setOnClickListener(v -> onBackPressed());

        // Handle Buy button click
        buttonBeli.setOnClickListener(v -> {
            List<CartItem> selectedItems = getSelectedItems();

            if (selectedItems.isEmpty()) {
                Toast.makeText(this, "Pilih produk terlebih dahulu", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(Keranjang1Activity.this, PemesananDetailActivity.class);
                intent.putParcelableArrayListExtra("cartData", new ArrayList<>(selectedItems)); // Send selected items to next activity

                int totalPrice = calculateTotalPrice(selectedItems);
                intent.putExtra("totalPrice", totalPrice);

                startActivity(intent);
            }
        });

    }

    private int calculateTotalPrice(List<CartItem> selectedItems) {
        int total = 0;
        for (CartItem item : selectedItems) {
            total += item.getProduct().getPrice() * item.getQuantity(); // Calculate total based on selected items
        }
        return total;
    }


    // Get selected items from the cart
    private List<CartItem> getSelectedItems() {
        List<CartItem> selectedItems = new ArrayList<>();
        for (CartItem item : cartItems) {
            if (item.isSelected()) {
                selectedItems.add(item);
            }
        }
        return selectedItems;
    }

    // Update total price based on selected items
    private void updateTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            if (item.isSelected()) { // Only count selected items
                total += item.getProduct().getPrice() * item.getQuantity();
            }
        }
        totalPriceTextView.setText("Total: Rp. " + total);
    }

    // Select or deselect all items in the cart
    private void selectAllItems(boolean select) {
        for (CartItem item : cartItems) {
            item.setSelected(select);
        }
    }

    @Override
    public void onCartUpdated() {
        updateTotalPrice(); // Update total price when the cart is updated
    }

    // Save cart items to SharedPreferences (if necessary)
    private void saveCartItemsToStorage(List<CartItem> cartItems) {
        SharedPreferences sharedPreferences = getSharedPreferences("KeranjangPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Convert List<CartItem> to JSON string
        Gson gson = new Gson();
        String json = gson.toJson(cartItems);

        editor.putString("cartItems", json);
        editor.apply();
    }

    // Load cart items from SharedPreferences (if necessary)
    private List<CartItem> getCartItemsFromStorage() {
        SharedPreferences sharedPreferences = getSharedPreferences("KeranjangPrefs", MODE_PRIVATE);
        String json = sharedPreferences.getString("cartItems", null);

        if (json != null) {
            Gson gson = new Gson();
            return gson.fromJson(json, new TypeToken<List<CartItem>>(){}.getType());
        }

        return new ArrayList<>();
    }
}
