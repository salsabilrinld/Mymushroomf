package com.example.mymushroomf.PembeliActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.PembeliAdapter.CartAdapter;
import com.example.mymushroomf.PembeliModel.CartItem;
import com.example.mymushroomf.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private List<CartItem> cartItems = new ArrayList<>();
    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang1); // Your cart layout

        // Setup RecyclerView
        cartRecyclerView = findViewById(R.id.recycler_viewkeranjang);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(cartItems);
        cartRecyclerView.setAdapter(cartAdapter);
    }

    // Method to add an item to the cart
    public void addItemToCart(CartItem item) {
        cartItems.add(item);
        cartAdapter.notifyDataSetChanged(); // Refresh the RecyclerView
    }

    // Method to remove an item from the cart (e.g., when deleting)
    public void removeItemFromCart(CartItem item) {
        cartItems.remove(item);
        cartAdapter.notifyDataSetChanged(); // Refresh the RecyclerView
    }
}

}