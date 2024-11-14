package com.example.mymushroomf;

import android.content.Intent;
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

public class OrderListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list); // Make sure this layout contains your RecyclerView

        // Initialize RecyclerView and set layout manager
        recyclerView = findViewById(R.id.recycler_vieworder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Set up the adapter and RecyclerView
        orderList = new ArrayList<>();
        orderList.add(new Order("Jamur Tiram", "salsabilrinld", R.drawable.jamur_tiram, "1 Produk", "Selesai", "Rp. 12.000"));


        orderAdapter = new OrderAdapter(orderList, new OrderAdapter.OnOrderClickListener() {
            @Override
            public void onOrderClick(Order order) {
                Intent intent = new Intent(OrderListActivity.this, OrderDetailActivity.class);
                intent.putExtra("order", order);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(orderAdapter);

        // Handle bottom navigation if present
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_store) {
                    Intent intent = new Intent(OrderListActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_transaction) {
                    Toast.makeText(OrderListActivity.this, "Transaction selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.menu_profile) {
                    Intent intent = new Intent(OrderListActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

}
