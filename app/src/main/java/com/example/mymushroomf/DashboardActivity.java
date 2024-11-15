package com.example.mymushroomf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymushroomf.PembeliActivity.NotificationsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
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
    private Spinner filterSpinner, sortSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button addProductButton = findViewById(R.id.button_addproduct);
        TextView welcomeText = findViewById(R.id.welcome_text);
        recyclerView = findViewById(R.id.recycler_myproduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ImageButton notificationsButton = findViewById(R.id.notifications_button);
        filterSpinner = findViewById(R.id.button_filter);
        sortSpinner = findViewById(R.id.button_sort);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "User");
        welcomeText.setText("Welcome, " + name + "!");

        notificationsButton.setOnClickListener(view -> {
            Intent notifIntent = new Intent(DashboardActivity.this, NotificationsActivity.class);
            startActivity(notifIntent);
        });

        recyclerView = findViewById(R.id.recycler_myproduct);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        productList = new ArrayList<>();
        filteredList = new ArrayList<>(productList);
        productList.add(new Product("Jamur Tiram", "Organic", "Rp. 9.500", Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_tiram).toString()));
        productList.add(new Product("Jamur Kuping", "Organic", "Rp. 12.000", Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_kuping).toString()));
        productList.add(new Product("Jamur Kancing", "Organic", "Rp. 7.000", Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_kancing).toString()));
        productList.add(new Product("Jamur Tiram", "Organic", "Rp. 9.500", Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_tiram).toString()));
        productList.add(new Product("Jamur Kuping", "Organic", "Rp. 12.000", Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_kuping).toString()));
        productList.add(new Product("Jamur Kancing", "Organic", "Rp. 7.000", Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_kancing).toString()));
        productList.add(new Product("Jamur Tiram", "Organic", "Rp. 9.500", Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_tiram).toString()));
        productList.add(new Product("Jamur Kuping", "Organic", "Rp. 12.000", Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_kuping).toString()));
        productList.add(new Product("Jamur Kancing", "Organic", "Rp. 7.000", Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_kancing).toString()));


        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);

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
                    Intent intent = new Intent(DashboardActivity.this, OrderListActivity.class);

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


    }

    private void setupFilterSpinner() {
        filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedType = filterSpinner.getSelectedItem().toString();
                productAdapter.filterByType(selectedType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setupSortSpinner() {
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        productAdapter.sortByPrice(true);
                        break;
                    case 1:
                        productAdapter.sortByPrice(false);
                        break;
                    case 2:
                        productAdapter.sortByName(true);
                        break;
                    case 3:
                        productAdapter.sortByName(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private List<Product> getProductList() {
        // Fetch or create your list of products here
        return new ArrayList<>();
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

        builder.setPositiveButton("Save", (alertDialog, which) -> {
            String jamurName = fungiName.getText().toString();
            String jamurType = fungiTypeSpinner.getSelectedItem().toString(); // Ambil tipe dari Spinner
            String jamurPrice = fungiPrice.getText().toString();
            String jamurDesc = ((EditText) findViewById(R.id.fungi_description)).getText().toString();
            String imageUrl = "https://example.com/jamur_tiram.jpg";

            // Buat objek produk dengan tipe yang dipilih
            Product newProduct = new Product(jamurName, jamurType, jamurPrice, imageUrl);
            addProductToList(newProduct);
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
            Collections.sort(filteredList, Comparator.comparing(Product::getPrice));
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
