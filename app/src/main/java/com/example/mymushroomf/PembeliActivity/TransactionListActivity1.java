package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.R;
import com.example.mymushroomf.Transaction1;
import com.example.mymushroomf.TransactionAdapter1;
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
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.menu_store) {
                    startActivity(new Intent(TransactionListActivity1.this, Dashboard1Activity.class));
                    // Store menu item clicked
                    return true;
                } else if (item.getItemId() == R.id.menu_transaction) {
                    // Transaction menu item clicked
                    Toast.makeText(TransactionListActivity1.this, "Transaction selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.menu_profile) {
                    // Profile menu item clicked
                    startActivity(new Intent(TransactionListActivity1.this, Profile1Activity.class));
                    return true;
                }
                return false; // Default return when no item matches
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