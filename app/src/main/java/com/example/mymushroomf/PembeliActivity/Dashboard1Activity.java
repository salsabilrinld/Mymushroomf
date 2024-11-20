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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.ApiClient;
import com.example.mymushroomf.PembeliModel.Produk1;
import com.example.mymushroomf.PembeliAdapter.ProdukAdapterPembeli;
import com.example.mymushroomf.PembeliModel.Users;
import com.example.mymushroomf.PembeliService.ProdukService;
import com.example.mymushroomf.PembeliService.UserService;
import com.example.mymushroomf.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        // Initialize Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_store) {
                // Store menu item clicked
                Toast.makeText(Dashboard1Activity.this, "Store selected", Toast.LENGTH_SHORT).show();
                return true;
            } else if (item.getItemId() == R.id.menu_transaction) {
                // Transaction menu item clicked
                startActivity(new Intent(Dashboard1Activity.this, OrderHistoryActivity.class));
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
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize product list and filtered product list
        productList = new ArrayList<>();
        filteredProductList = new ArrayList<>(productList);

        // Initialize the adapter
//        produkAdapterPembeli = new ProdukAdapterPembeli(this, productList);
//        recyclerView.setAdapter(produkAdapterPembeli);

        produkAdapterPembeli = new ProdukAdapterPembeli(productList);
        recyclerView.setAdapter(produkAdapterPembeli);
        // Fetch products from API
        fetchProducts();

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
        filteredProductList.clear();

        if (query != null && !query.trim().isEmpty()) {
            for (Produk1 product : productList) {
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
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://mushroom.miauwlan.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ProdukService service = retrofit.create(ProdukService.class);
        Call<List<Produk1>> call = service.getProducts();
        call.enqueue(new Callback<List<Produk1>>() {
            @Override
            public void onResponse(Call<List<Produk1>> call, Response<List<Produk1>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        productList.clear();
                        productList.addAll(response.body());
                        produkAdapterPembeli.updateProductList(productList);
                    } else {
                        Log.e("Dashboard1Activity", "Response body is null");
                        Toast.makeText(Dashboard1Activity.this, "Response body is empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("Dashboard1Activity", "HTTP error: " + response.code());
                    Toast.makeText(Dashboard1Activity.this, "HTTP error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
                Log.d("API Response", "Data: " + new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<List<Produk1>> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.e("Dashboard1Activity", "Network failure: " + t.getMessage());
                    Toast.makeText(Dashboard1Activity.this, "Network failure, please try again", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("Dashboard1Activity", "Conversion error: " + t.getMessage());
                    Toast.makeText(Dashboard1Activity.this, "Unexpected error occurred", Toast.LENGTH_SHORT).show();
                }
                Log.e("API Error", "Error: ", t);
            }
        });
    }
}
