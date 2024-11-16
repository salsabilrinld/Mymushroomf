package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
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
    private static final String SHARED_PREFS_NAME = "userPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactionlist1);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_viewtransaction);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        allTransactionList.add(new Transaction1("1", "Jamur Tiram", "Organik", "Selesai", "Rp. 9.500", Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_tiram).toString()));
        allTransactionList.add(new Transaction1("2", "Jamur Kuping", "Organik", "Selesai", "Rp. 12.000", Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_kuping).toString()));
        allTransactionList.add(new Transaction1("3", "Jamur Merang", "Organik", "Dalam Perjalanan", "Rp. 7.000",Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_kancing).toString()));


        // Initialize transaction list and adapter
        allTransactionList = new ArrayList<>();
        transactionAdapter = new TransactionAdapter1(this, allTransactionList);
        recyclerView.setAdapter(transactionAdapter);

        Button buttonAll = findViewById(R.id.simple_button_all);
        Button buttonDelivered = findViewById(R.id.simple_button_delivered);
        Button buttonInProgress = findViewById(R.id.simple_button_in_progress);
        Button buttonCanceled = findViewById(R.id.simple_button_canceled);

        buttonAll.setOnClickListener(v -> transactionAdapter.filter("semua"));
        buttonDelivered.setOnClickListener(v -> transactionAdapter.filter("Pengiriman Selesai"));
        buttonInProgress.setOnClickListener(v -> transactionAdapter.filter("Dalam Pengiriman"));
        buttonCanceled.setOnClickListener(v -> transactionAdapter.filter("Dibatalkan"));

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

}