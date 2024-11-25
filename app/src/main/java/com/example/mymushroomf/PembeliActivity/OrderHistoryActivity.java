package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.R;
import com.example.mymushroomf.PembeliModel.Order;
import com.example.mymushroomf.PembeliAdapter.OrderAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchView searchEditText;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactionlist1);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_viewtransaction);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchEditText = findViewById(R.id.searchEditText);


        Date currentDate = new Date();

        orderList = new ArrayList<>();
//        orderList.add(new Order("1", Arrays.asList(
//                new OrderDetail("d1", new Produk1("1", "Jamur Tiram", null, "Jamur Mentah", "100 kg", 12000, R.drawable.jamur_tiram),
//                        "Selesai", "JNE", "123", "Bogor", "Indira", "0821", new Date(), 1, 12000, 7000, 19000)
//        )));
//        orderList.add(new Order("2", Arrays.asList(
//                new OrderDetail("d2", new Produk1("2", "Jamur Kancing", null, "Jamur Mentah", "200 kg", 9000, R.drawable.jamur_kancing),
//                        "Selesai", "JNE", "124", "Jakarta", "Budi", "0812", new Date(), 2, 9000, 5000, 24000)
//        )));
//        orderList.add(new Order("2", Arrays.asList(
//                new OrderDetail("d3", new Produk1("3", "Jamur Kuping", null, "Jamur Mentah", "200 kg", 9000, R.drawable.jamur_kancing),
//                        "Dalam Perjalanan", "JNE", "124", "Jakarta", "Budi", "0812", new Date(), 2, 9000, 5000, 24000)
//        )));

        orderAdapter = new OrderAdapter(this, orderList);
//            Intent intent = new Intent(OrderHistoryActivity.this, OrderDetailActivity.class);
//            intent.putExtra("orderId", order.getId());
//            intent.putExtra("productName", order.getProductName());
//            intent.putExtra("totalPrice", order.getTotalPrice());
//            intent.putExtra("orderStatus", order.getStatus());
//            intent.putExtra("orderDate", order.getOrderDate());
//            startActivity(intent);
//        });
        recyclerView.setAdapter(orderAdapter);


        searchEditText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform search when the user submits the query
                filterOrders(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Perform search as the user types
                filterOrders(newText);
                return true;
            }
        });


        // Add filter buttons
        Button buttonAll = findViewById(R.id.simple_button_all);
        Button buttonDelivered = findViewById(R.id.simple_button_delivered);
        Button buttonInProgress = findViewById(R.id.simple_button_in_progress);
        Button buttonCanceled = findViewById(R.id.simple_button_canceled);

        OrderAdapter orderAdapter = new OrderAdapter(this, orderList);
        recyclerView.setAdapter(orderAdapter);

        buttonAll.setOnClickListener(v -> orderAdapter.filter("Semua"));
        buttonDelivered.setOnClickListener(v -> orderAdapter.filter("Selesai"));
        buttonInProgress.setOnClickListener(v -> orderAdapter.filter("Dalam Perjalanan"));
        buttonCanceled.setOnClickListener(v -> orderAdapter.filter("Dibatalkan"));

        // BottomNavigationView setup
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.menu_store) {
                startActivity(new Intent(OrderHistoryActivity.this, DashboardActivity.class));
                return true;
            } else if (itemId == R.id.menu_transaction) {
                Toast.makeText(OrderHistoryActivity.this, "Transaction selected", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemId == R.id.menu_profile) {
                startActivity(new Intent(OrderHistoryActivity.this, Profile1Activity.class));
                return true;
            } else {
                return false;
            }
        });

        // Set default selected item
        bottomNavigationView.setSelectedItemId(R.id.menu_transaction);
    }

    private void filterOrders(String query) {
        List<Order> filteredList = new ArrayList<>();
        for (Order order : orderList) {
            // Check if the order's product name or order ID matches the query
            if (order.getOrderDetails().get(0).getProduct().getProduct_name().toLowerCase().contains(query.toLowerCase()) ||
                    order.getOrderId().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(order);
            }
        }
        orderAdapter = new OrderAdapter(this, filteredList);
        recyclerView.setAdapter(orderAdapter);
    }
}