package com.example.mymushroomf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class TransactionListActivity1 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransactionAdapter1 transactionAdapter;
    private List<Transaction1> transactionList;
    private static final String SHARED_PREFS_NAME = "userPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_list);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_viewtransaction);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize transaction list and adapter
        transactionList = new ArrayList<>();
        transactionAdapter = new TransactionAdapter1(this, transactionList);
        recyclerView.setAdapter(transactionAdapter);

        // Load transaction data
        loadTransactionData();

        // Set up BottomNavigationView and handle item selection
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu_transaction);

        // Setting listener for bottom navigation items
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Access role from SharedPreferences each time an item is selected
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
                String role = sharedPreferences.getString("role", ""); // Retrieve role

                if (item.getItemId() == R.id.menu_store) {
                    // Handle store menu item click
                    Toast.makeText(TransactionListActivity1.this, "Store selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.menu_transaction) {
                    // Handle transaction menu item click, check the role
                    if ("Pembeli".equals(role)) {
                        // If role is "Pembeli", allow access to TransactionListActivity
                        Intent intent = new Intent(TransactionListActivity1.this, TransactionListActivity1.class);
                        startActivity(intent);
                        return true;
                    } else {
                        // If role is not "Pembeli", show access denied message
                        Toast.makeText(TransactionListActivity1.this, "Akses ditolak: hanya Pembeli", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                } else if (item.getItemId() == R.id.menu_profile) {
                    // Handle profile menu item click
                    Intent intent = new Intent(TransactionListActivity1.this, Profile1Activity.class);
                    startActivity(intent);
                    return true;
                }

                return false;
            }
        });
    }

    private void loadTransactionData() {
        // Clear list to avoid duplication
        transactionList.clear();

        // Add sample transaction data
        transactionList.add(new Transaction1(
                "Jamur Tiram",
                "Type A",
                "Selesai",
                "Rp. 12.000",
                Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_tiram).toString()
        ));

        transactionList.add(new Transaction1(
                "Jamur Tiram",
                "Type A",
                "Selesai",
                "Rp. 12.000",
                "https://example.com/jamur_tiram.jpg"
        ));

        // Notify adapter of data changes
        transactionAdapter.notifyDataSetChanged();
    }
}