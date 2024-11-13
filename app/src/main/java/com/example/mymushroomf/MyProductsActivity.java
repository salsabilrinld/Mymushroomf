package com.example.mymushroomf;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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
    private Spinner filterSpinner, sortSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_myproducts);
        Log.d("MyProductsActivity", "Activity opened successfully");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_product), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        filterSpinner = findViewById(R.id.button_filter);
        sortSpinner = findViewById(R.id.button_sort);

        productRecyclerView = findViewById(R.id.recycler_myproduct);
        productRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        productList = new ArrayList<>();
        filteredList = new ArrayList<>(productList);
        productList.add(new Product("Jamur Tiram", "Organic", "Rp. 9.500", Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_tiram).toString()));
        productList.add(new Product("Jamur Kuping", "Organic", "Rp. 12.000", Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_kuping).toString()));
        productList.add(new Product("Jamur Kancing", "Organic", "Rp. 7.000", Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_kancing).toString()));


        productAdapter = new ProductAdapter(this, productList);
        productRecyclerView.setAdapter(productAdapter);

        ImageButton backButton = findViewById(R.id.back_myproducts);
        Button newButton = findViewById(R.id.button_addproduct);

        setupFilterSpinner();
        setupSortSpinner();


        backButton.setOnClickListener(view -> finish());

        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddProductDialog();
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
            String jamurDesc = fungiDesc.getText().toString();
            String imageUrl = "https://example.com/jamur_tiram.jpg";

            // Buat objek produk dengan tipe yang dipilih
            Product newProduct = new Product(jamurName, jamurType, jamurPrice, imageUrl);
            addProductToList(newProduct);

        }).setIcon(R.drawable.border_button);

        builder.setNegativeButton("Cancel", (alertDialog, which) -> alertDialog.dismiss());
        builder.create().show();
    }

    private void addProductToList(Product product) {
        productList.add(product);
        productAdapter.notifyDataSetChanged();
    }


}
