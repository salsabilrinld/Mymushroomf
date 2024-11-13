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

public class TransactionListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransactionAdapter transactionAdapter;
    private OrderDetailAdapter orderDetailAdapter;
    private List<Transaction> transactionList;
    private List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_list);

        recyclerView = findViewById(R.id.recycler_viewtransaction);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        transactionList = new ArrayList<>();
        transactionList.add(new Transaction("1", "Jamur Tiram", "1 Produk", "Selesai", "Rp. 12.000", Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_tiram).toString()));

        orderList = new ArrayList<>();
        orderList.add(new Order("1", "Selesai", "Jamur Tiram", "Reguler", "123", "Perumahan Bogor", "BCA", "Rp. 12.000", "Rp. 7.000", "Rp. 19.000"));


        transactionAdapter = new TransactionAdapter(this, transactionList, new TransactionAdapter.OnItemClickListener() {
            public void onItemClick(int position) {
                // Pindah ke OrderDetailActivity saat item diklik
                Intent intent = new Intent(TransactionListActivity.this, OrderDetailActivity.class);
                // Kirim detail pesanan dari orderList berdasarkan posisi item yang diklik
                intent.putExtra("order_detail", orderList.get(position));
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(transactionAdapter);


        loadTransactionData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu_transaction);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_store) {
                    Intent intent = new Intent(TransactionListActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_transaction) {
                    Toast.makeText(TransactionListActivity.this, "Transaction selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (item.getItemId() == R.id.menu_profile) {
                    Intent intent = new Intent(TransactionListActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });

    }

    private void loadTransactionData() {
        // Isi data transaksi ke dalam transactionList
        transactionList.add(new Transaction("2", "Jamur Kuping", "1 Produk", "Selesai", "Rp. 12.000", Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.jamur_kuping).toString()));
        orderList.add(new Order("1", "Selesai", "Jamur Tiram", "Reguler", "123", "Perumahan Bogor", "BCA", "Rp. 12.000", "Rp. 7.000", "Rp. 19.000"));
        transactionAdapter.notifyDataSetChanged();
    }

}

