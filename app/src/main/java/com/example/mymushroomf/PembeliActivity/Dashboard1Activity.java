package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.PembeliModel.Produk1;
import com.example.mymushroomf.PembeliAdapter.ProdukAdapterPembeli;
import com.example.mymushroomf.R;
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
        welcomeTextLine1 = findViewById(R.id.welcome_text_line1);
        welcomeTextLine2 = findViewById(R.id.welcome_text_line2);

        // Mendapatkan nama pengguna dari SharedPreferences
        String name = sharedPreferences.getString("name", "User");
        welcomeTextLine1.setText("Hey, " + name + "!");
        welcomeTextLine2.setText("Let's go shopping!");

        // Handle Bottom Navigation Selection
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
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
        });

        // Initialize SearchView and Buttons
        searchView = findViewById(R.id.searchEditText);
        ImageButton notificationsButton = findViewById(R.id.notifications_button);
        ImageButton keranjangButton = findViewById(R.id.keranjang_button);

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recycler_viewproduct);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Initialize product list
        productList = new ArrayList<>();
        filteredProductList = new ArrayList<>(productList);

        // Adding sample products
        productList.add(new Produk1("Jamur Tiram", "Deskripsi Jamur Tiram", "Organic", 9500, R.drawable.jamur_tiram));
        productList.add(new Produk1("Jamur Kuping", "Deskripsi Jamur Kuping", "Organic", 12000, R.drawable.jamur_kuping));
        productList.add(new Produk1("Jamur Kancing", "Deskripsi Jamur Kancing", "Nonorganik", 7000, R.drawable.jamur_kancing));

        // Initialize the adapter
        produkAdapterPembeli = new ProdukAdapterPembeli(this, productList);
        recyclerView.setAdapter(produkAdapterPembeli);

        // Set SearchView listener to filter products based on user input
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Call filterProducts to filter products based on user query
                filterProducts(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Call filterProducts to filter products as user types
                filterProducts(newText);
                return true;
            }
        });

        // Handling notifications and cart buttons
        notificationsButton.setOnClickListener(view -> {
            Intent notifIntent = new Intent(Dashboard1Activity.this, NotificationsActivity.class);
            startActivity(notifIntent);
        });

        keranjangButton.setOnClickListener(view -> {
            Intent keranjangIntent = new Intent(Dashboard1Activity.this, Keranjang1Activity.class);
            startActivity(keranjangIntent);
        });
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
        produkAdapterPembeli.updateProductList(filteredProductList);
    }
}
