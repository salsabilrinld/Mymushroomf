package com.example.mymushroomf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private MyAdapter myAdapter;
    private LinearLayout layoutFilter, layoutSort;
    private List<Product> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        layoutFilter = findViewById(R.id.filter_button);
        layoutSort = findViewById(R.id.sort_button);
        Button addProductButton = findViewById(R.id.button_addproduct);
        TextView welcomeText = findViewById(R.id.welcome_text);
        recyclerView = findViewById(R.id.recycler_viewproduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "User");
        welcomeText.setText("Welcome, " + name + "!");

        layoutFilter.setOnClickListener(v -> showFilterDialog());

        layoutSort.setOnClickListener(v -> showSortDialog());

        productList = new ArrayList<>();
        filteredList = new ArrayList<>(productList);
        productAdapter = new ProductAdapter(this, filteredList);
        recyclerView.setAdapter(productAdapter);

        productList.add(new Product("Jamur Tiram", "Deskripsi produk 1", 15.000, Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_tiram).toString()));
        // null untuk gambar
        productAdapter.notifyDataSetChanged();

        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddProductDialog();
            }
    });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_store) {
                    Toast.makeText(DashboardActivity.this, "Store selected", Toast.LENGTH_SHORT).show();
//                    bottomNavigationView.setSelectedItemId(R.id.menu_store);
                    return true;
                } else if (item.getItemId() == R.id.menu_transaction) {
                    Intent intent = new Intent(DashboardActivity.this, TransactionListActivity.class);

                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_profile) {
                    Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                    startActivity(intent);
//                    bottomNavigationView.setSelectedItemId(R.id.menu_profile);
                    return true;
                } else {
                    return false;
                }
            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        List<String> myData = Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6");
        myAdapter = new MyAdapter(myData);
        recyclerView.setAdapter(myAdapter);

    }



    private void showAddProductDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_product, null);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();

        EditText fungiName = dialogView.findViewById(R.id.fungi_name);
        Spinner fungiTypeSpinner = dialogView.findViewById(R.id.fungi_type);
        EditText fungiPrice = dialogView.findViewById(R.id.fungi_price);
        EditText fungiDesc = dialogView.findViewById(R.id.fungi_description);
        Button imageButton = dialogView.findViewById(R.id.upload_photo_button);
        Button saveButton = dialogView.findViewById(R.id.save_button);
        Button cancelButton = dialogView.findViewById(R.id.cancel_button);

        builder.setPositiveButton("Save", (alertDialog, which) -> {
            String jamurName = fungiName.getText().toString();
            String jamurType = fungiTypeSpinner.getSelectedItem().toString(); // Ambil tipe dari Spinner
            double jamurPrice = Double.parseDouble(fungiPrice.getText().toString());
            String jamurDesc = ((EditText) findViewById(R.id.fungi_description)).getText().toString();
            String imageUrl = "https://example.com/jamur_tiram.jpg";

            // Buat objek produk dengan tipe yang dipilih
            Product newProduct = new Product(jamurName, jamurType, jamurPrice, imageUrl);
            addProductToList(newProduct);

            filterAndSortProducts();
        });

        builder.setNegativeButton("Cancel", (alertDialog, which) -> alertDialog.dismiss());
        builder.create().show();
    }

    private void addProductToList(Product product) {
        productList.add(product);
        productAdapter.notifyDataSetChanged();
    }


    private void showFilterDialog() {
        String[] filterOptions = {"All", "Organik", "Nonorganik"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Filter")
                .setItems(filterOptions, (dialog, which) -> {
                    String selectedFilter = filterOptions[which];
                    filterProducts(selectedFilter);
                })
                .create()
                .show();
    }

    private void filterProducts(String filter) {
        filteredList.clear(); // Kosongkan daftar produk yang sedang ditampilkan

        if (filter.equals("All")) {
            filteredList.addAll(productList); // Menambahkan semua produk
        } else {
            // Menambahkan produk yang sesuai dengan filter
            for (Product product : productList) {
                if (product.getType().equals(filter)) {
                    filteredList.add(product);
                }
            }
        }

        // Memperbarui tampilan RecyclerView setelah filter diterapkan
        productAdapter.notifyDataSetChanged();
    }


    private void showSortDialog() {
        String[] sortOptions = {"Sort by Name", "Sort by Price"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Sort Option")
                .setItems(sortOptions, (dialog, which) -> {
                    String selectedSort = sortOptions[which];
                    sortProducts(selectedSort);
                })
                .create()
                .show();
    }

    private void sortProducts(String sortOption) {
        if (sortOption.equals("Sort by Name")) {
            // Mengurutkan berdasarkan nama produk
            Collections.sort(filteredList, Comparator.comparing(Product::getName));
        } else if (sortOption.equals("Sort by Price")) {
            // Mengurutkan berdasarkan harga produk
            Collections.sort(filteredList, Comparator.comparingDouble(Product::getPrice));
        }

        // Memperbarui tampilan RecyclerView setelah produk diurutkan
        productAdapter.notifyDataSetChanged();
    }

    private void filterAndSortProducts() {
        // Terapkan filter dan sortir secara bersamaan
        filterProducts("All");  // Misalnya, filter berdasarkan "All"
        sortProducts("Sort by Name");  // Misalnya, sortir berdasarkan nama
    }

//            cancelButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dialog.dismiss();
//                }
//            });
//
//            dialog.show();
//            }
//
//            private void updateDashboard() {
//        productAdapter.notifyDataSetChanged();
//    }

//    @Override
//    public void onEditClick(int position) {
//        // Logika untuk mengedit produk
//    }
//
//    @Override
//    public void onDeleteClick(int position) {
//        // Logika untuk menghapus produk
//        productList.remove(position);
//        productAdapter.notifyItemRemoved(position);
//    }
//
//    public void updateProductList(Product product) {
//        productList.add(product);
//        productAdapter.notifyItemInserted(productList.size() - 1);
//    }
}
