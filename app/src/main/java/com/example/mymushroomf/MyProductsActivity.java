package com.example.mymushroomf;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyProductsActivity extends AppCompatActivity {

    private RecyclerView productRecyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myproducts);  // Pastikan layout ini mengandung RecyclerView

        // Inisialisasi RecyclerView
        productRecyclerView = findViewById(R.id.recyler_myproduct);
        productRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Inisialisasi Data Produk
        productList = new ArrayList<>();
        productList.add(new Product("Jamur Tiram", "Organic", 9.500, Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_tiram).toString()));
        productList.add(new Product("Jamur Kuping", "Organic", 12.000, Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_kuping).toString()));
        productList.add(new Product("Jamur Kancing", "Organic", 7.000, Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_kancing).toString()));
        // Tambahkan produk lain sesuai kebutuhan

        // Set Adapter untuk RecyclerView
        productAdapter = new ProductAdapter(this, productList);
        productRecyclerView.setAdapter(productAdapter);
    }
}
