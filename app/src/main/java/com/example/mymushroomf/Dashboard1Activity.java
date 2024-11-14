package com.example.mymushroomf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Dashboard1Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProdukAdapterPembeli produkAdapterPembeli;
    private List<Produk1> productList;
    private List<Produk1> filteredProductList;
    private SearchView searchView;
    private TextView welcomeTextLine1;
    private TextView welcomeTextLine2;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard1);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String role = sharedPreferences.getString("role", ""); // Retrieve the user's role
        String username = sharedPreferences.getString("username", "User"); // Retrieve username
        welcomeTextLine1 = findViewById(R.id.welcome_text_line1);
        welcomeTextLine2 = findViewById(R.id.welcome_text_line2);

        // Handle Bottom Navigation Selection
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.menu_store) {
                    // Store menu item clicked
                    Toast.makeText(Dashboard1Activity.this, "Store selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.menu_transaction) {
                    // Transaction menu item clicked
                    startActivity(new Intent(Dashboard1Activity.this, TransactionListActivity1.class));
                    return true;
                } else if (item.getItemId() == R.id.menu_profile) {
                    // Profile menu item clicked
                    startActivity(new Intent(Dashboard1Activity.this, Profile1Activity.class));
                    return true;
                }
                return false; // Default return when no item matches
            }
        });

        if (role.equals("Pembeli")) {
            // If role is Pembeli (Buyer), setup buyer dashboard
            setupDashboardPembeli();
        } else if (role.equals("Penjual")) {
            // If role is Penjual (Seller), redirect to seller dashboard
            navigateToSellerDashboard();
        } else {
            // Handle case where the role is undefined (if any)
            Toast.makeText(this, "Invalid role", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupDashboardPembeli() {
        // Set up UI elements for the buyer's dashboard
        TextView welcomeTextLine1 = findViewById(R.id.welcome_text_line1);
        TextView welcomeTextLine2 = findViewById(R.id.welcome_text_line2);
        searchView = findViewById(R.id.searchEditText);
        recyclerView = findViewById(R.id.recycler_viewproduct);

        // Set RecyclerView to use GridLayout with 2 columns
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Prepare product list for display
        productList = new ArrayList<>();
        productList.add(new Produk1("Jamur Tiram", "500g", "15,000", R.drawable.jamur_tiram)); // Example product

        // Create a filtered product list (initially the same as the product list)
        filteredProductList = new ArrayList<>(productList);

        // Initialize the adapter and set it to RecyclerView
        produkAdapterPembeli = new ProdukAdapterPembeli(this, filteredProductList);
        recyclerView.setAdapter(produkAdapterPembeli);

        // Set SearchView listener to filter products based on user input
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterProducts(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterProducts(newText);
                return true;
            }
        });

        // Display a personalized welcome message
        String userName = sharedPreferences.getString("username", "User");
        welcomeTextLine1.setText("Hey, " + userName);
        welcomeTextLine2.setText("Let's Go Shopping!");
    }

    private void filterProducts(String query) {
        // Clear the filtered product list before refilling it
        filteredProductList.clear();

        if (query != null && !query.trim().isEmpty()) {
            // Add products whose name contains the query text
            for (Produk1 product : productList) {
                if (product.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredProductList.add(product);
                }
            }
        } else {
            // If the query is empty, show all products
            filteredProductList.addAll(productList);
        }

        // Notify adapter that the dataset has changed
        produkAdapterPembeli.notifyDataSetChanged();
    }

    private void navigateToSellerDashboard() {
        // Redirect to the seller's dashboard activity
        startActivity(new Intent(Dashboard1Activity.this, DashboardActivity.class));
        finish(); // Close the current activity (dashboard for buyer)
    }
}