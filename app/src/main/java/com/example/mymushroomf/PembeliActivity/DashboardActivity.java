package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.ApiClient;
import com.example.mymushroomf.PembeliModel.Produk;
import com.example.mymushroomf.PembeliAdapter.ProdukAdapterPembeli;
import com.example.mymushroomf.PembeliService.ProdukService;
import com.example.mymushroomf.R;
import com.example.mymushroomf.formlogin;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProdukAdapterPembeli produkAdapterPembeli;
    private List<Produk> productList;
    private List<Produk> filteredProductList;
    private SearchView searchView;
    private TextView welcomeTextLine1;
    private TextView welcomeTextLine2;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard1);


        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);  // Default to null if no token exists

        if (token == null) {
            Intent intent = new Intent(DashboardActivity.this, formlogin.class);
            startActivity(intent);
            finish(); // Menutup DashboardActivity jika belum login
        } else {
        }

        welcomeTextLine1 = findViewById(R.id.welcome_text_line1);
        welcomeTextLine2 = findViewById(R.id.welcome_text_line2);


        String name = sharedPreferences.getString("name", "User");
        welcomeTextLine1.setText("Halo " + name + ",");
        welcomeTextLine2.setText("Selamat Datang!");

        // Initialize Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_store) {
                // Store menu item clicked
                Toast.makeText(DashboardActivity.this, "Store selected", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.menu_transaction) {
                // Transaction menu item clicked
                startActivity(new Intent(DashboardActivity.this, OrderHistoryActivity.class));
                return true;
            } else if (item.getItemId() == R.id.menu_profile) {
                // Profile menu item clicked
                startActivity(new Intent(DashboardActivity.this, Profile1Activity.class));
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

        productList = new ArrayList<>();
        filteredProductList = new ArrayList<>(productList);

        // Fetch products from API
        fetchProducts();

        produkAdapterPembeli = new ProdukAdapterPembeli(productList);
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

        notificationsButton.setOnClickListener(view -> {
            Intent notifIntent = new Intent(DashboardActivity.this, NotificationsActivity.class);
            startActivity(notifIntent);
        });

        keranjangButton.setOnClickListener(view -> {
            Intent keranjangIntent = new Intent(DashboardActivity.this, Keranjang1Activity.class);
            startActivity(keranjangIntent);
        });
    }

    private void filterProducts(String query) {
        filteredProductList.clear();

        if (query != null && !query.trim().isEmpty()) {
            for (Produk product : productList) {
                if (product.getProduct_name().toLowerCase().contains(query.toLowerCase())) {
                    filteredProductList.add(product);
                }
            }
        } else {
            filteredProductList.addAll(productList);
        }

        produkAdapterPembeli.updateProductList(filteredProductList);
    }

    private void fetchProducts() {
        ProdukService service = ApiClient.getProdukService();
        Call<List<Produk>> call = service.getProducts();
        call.enqueue(new Callback<List<Produk>>() {
            @Override
            public void onResponse(Call<List<Produk>> call, Response<List<Produk>> response) {
                Log.d("API Response", "Data: " + new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        productList.clear();
                        productList = response.body();
                        produkAdapterPembeli.updateProductList(productList);
                    } else {
                        Log.e("DashboardActivity", "Response body is null");
                        Toast.makeText(DashboardActivity.this, "Response body is empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("DashboardActivity", "HTTP error: " + response.code());
                    Toast.makeText(DashboardActivity.this, "HTTP error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
                Log.d("API Response", "Data: " + new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<List<Produk>> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e("DashboardActivity", "Network failure: " + t.getMessage());
                    Toast.makeText(DashboardActivity.this, "Network failure, please try again", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("DashboardActivity", "Conversion error: " + t.getMessage());
                    Toast.makeText(DashboardActivity.this, "Unexpected error occurred", Toast.LENGTH_SHORT).show();
                }
                Log.e("API Error", "Error: ", t);
            }
        });
    }



}
