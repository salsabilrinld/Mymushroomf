package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mymushroomf.R;
import com.example.mymushroomf.PembeliModel.Transaction1;
import com.example.mymushroomf.PembeliAdapter.TransactionAdapter1;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class TransactionListActivity1 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransactionAdapter1 transactionAdapter;
    private List<Transaction1> allTransactionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactionlist1);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_viewtransaction);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize adapter
        transactionAdapter = new TransactionAdapter1(this, allTransactionList);
        recyclerView.setAdapter(transactionAdapter);

        // Add filter buttons
        Button buttonAll = findViewById(R.id.simple_button_all);
        Button buttonDelivered = findViewById(R.id.simple_button_delivered);
        Button buttonInProgress = findViewById(R.id.simple_button_in_progress);
        Button buttonCanceled = findViewById(R.id.simple_button_canceled);

        buttonAll.setOnClickListener(v -> transactionAdapter.filter("semua"));
        buttonDelivered.setOnClickListener(v -> transactionAdapter.filter("Pengiriman Selesai"));
        buttonInProgress.setOnClickListener(v -> transactionAdapter.filter("Dalam Pengiriman"));
        buttonCanceled.setOnClickListener(v -> transactionAdapter.filter("Dibatalkan"));

        // BottomNavigationView setup
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_store) {
                startActivity(new Intent(TransactionListActivity1.this, Dashboard1Activity.class));
                return true;
            } else if (itemId == R.id.menu_transaction) {
                Toast.makeText(TransactionListActivity1.this, "Transaction selected", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemId == R.id.menu_profile) {
                startActivity(new Intent(TransactionListActivity1.this, Profile1Activity.class));
                return true;
            } else {
                return false;
            }
        });

        // Check if data was passed through Intent
        if (getIntent().hasExtra("product_name") && getIntent().hasExtra("status") &&
                getIntent().hasExtra("price") && getIntent().hasExtra("image_uri")) {

            // Retrieve the data from Intent
            String productName = getIntent().getStringExtra("product_name");
            String status = getIntent().getStringExtra("status");
            String price = getIntent().getStringExtra("price");
            String imageUri = getIntent().getStringExtra("image_uri");

            // Create a new transaction object
            Transaction1 newTransaction = new Transaction1(
                    String.valueOf(allTransactionList.size() + 1), // Generate ID
                    productName, status, price, imageUri
            );

            // Add the new transaction to the list
            allTransactionList.add(newTransaction);
            transactionAdapter.notifyItemInserted(allTransactionList.size() - 1); // Update RecyclerView
        }

        // Set default selected item
        bottomNavigationView.setSelectedItemId(R.id.menu_transaction);
    }
}
