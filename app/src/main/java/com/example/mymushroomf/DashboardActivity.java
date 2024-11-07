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
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button addProductButton = findViewById(R.id.button_addproduct);
        TextView welcomeText = findViewById(R.id.welcome_text);
        recyclerView = findViewById(R.id.recycler_viewproduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "User");
        welcomeText.setText("Welcome, " + name + "!");

        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);

        // Ambil data produk dari database atau dari Intent
        // Misalnya menambahkan produk statis untuk contoh
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
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fungiName = ((EditText) findViewById(R.id.fungi_name)).getText().toString();
                String fungiType = fungiTypeSpinner.getSelectedItem().toString(); // Ambil tipe dari Spinner
                String fungiPrice = ((EditText) findViewById(R.id.fungi_price)).getText().toString();
                String fungiDesc = ((EditText) findViewById(R.id.fungi_description)).getText().toString();
                String imageUrl = "https://example.com/jamur_tiram.jpg";

                // Buat objek produk dengan tipe yang dipilih
                Product product = new Product(fungiName, fungiType, Double.parseDouble(fungiPrice), imageUrl);
                productList.add(new Product(fungiName, fungiType, Double.parseDouble(fungiPrice), imageUrl));

                updateDashboard();

                dialog.dismiss();
            }
        });
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dialog.show();
            }

            private void updateDashboard() {
        productAdapter.notifyDataSetChanged();
    }

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
