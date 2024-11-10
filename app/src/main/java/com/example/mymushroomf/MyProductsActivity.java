package com.example.mymushroomf;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyProductsActivity extends AppCompatActivity {

    private RecyclerView productRecyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private List<Product> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myproducts);

        productRecyclerView = findViewById(R.id.recyler_myproduct);
        productRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        productList = new ArrayList<>();
        filteredList = new ArrayList<>(productList);
        productList.add(new Product("Jamur Tiram", "Organic", 9.500, Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_tiram).toString()));
        productList.add(new Product("Jamur Kuping", "Organic", 12.000, Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_kuping).toString()));
        productList.add(new Product("Jamur Kancing", "Organic", 7.000, Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_kancing).toString()));


        productAdapter = new ProductAdapter(this, productList);
        productRecyclerView.setAdapter(productAdapter);

        ImageButton backButton = findViewById(R.id.back_myproducts);
        Button newButton = findViewById(R.id.button_addproduct);


        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(MyProductsActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddProductDialog();
            }
        });

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
        filterProducts("All");
        sortProducts("Sort by Name");
    }
}
