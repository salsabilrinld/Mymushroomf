package com.example.mymushroomf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardActivity extends AppCompatActivity implements ProductAdapter.OnProductClickListener {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;
    private MyAdapter myAdapter;

    @Override
    public void onProductClick(Product product) {
        // Handle product click event
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("PRODUCT_KEY", product); // Ensure Product implements Parcelable
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize views
        Button addProductButton = findViewById(R.id.button_addproduct);
        TextView welcomeText = findViewById(R.id.welcome_text);
        SearchView searchView = findViewById(R.id.searchEditText);
        recyclerView = findViewById(R.id.recycler_view); // Correctly initializing recyclerView

        // Set up welcome message
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "User");
        welcomeText.setText("Hey, " + name + "!");

        // Initialize product list
        productList = new ArrayList<>();
        productList.add(new Product("Jamur Tiram", "Deskripsi produk 1", "Rp. 15,000", R.drawable.jamur_tiram));
        productList.add(new Product("Jamur Tiram", "Deskripsi produk 2", "Rp. 15,000", R.drawable.jamur_tiram));
        productList.add(new Product("Jamur Tiram", "Deskripsi produk 3", "Rp. 15,000", R.drawable.jamur_tiram));

        // Set up RecyclerView
        adapter = new ProductAdapter(this, productList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        // Set up the button to add products
        addProductButton.setOnClickListener(view -> {
            Intent intent = new Intent(DashboardActivity.this, AddProductActivity.class);
            startActivity(intent);
        });

        // Set up BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_store:
                    return true; // Do nothing, stay on current activity
                case R.id.menu_transaction:
                    Intent transactionIntent = new Intent(DashboardActivity.this, TransactionListActivity.class);
                    startActivity(transactionIntent);
                    finish(); // Optional: finish current activity
                    return true;
                default:
                    return false;
            }
        });

        // Initialize second RecyclerView for additional data if needed
        // Assuming you have a second RecyclerView (if intended)
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        List<String> myData = Arrays.asList("Item 1", "Item 2", "Item 3");
        myAdapter = new MyAdapter(myData);
        recyclerView.setAdapter(myAdapter);

        // Set up the search view
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Assuming myAdapter is initialized for the search function
                // myAdapter.filter(newText);
                return true;
            }
        });

        // Load and display product data from SharedPreferences
        SharedPreferences sharedPreferences1 = getSharedPreferences("MyMushroomF", MODE_PRIVATE);
        String fungiName = sharedPreferences1.getString("fungiName", "No Name");
        Toast.makeText(this, "Saved Product: " + fungiName, Toast.LENGTH_SHORT).show();
    }
}
