package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageButton;  // Ganti ImageView menjadi ImageButton

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.PembeliModel.Address;
import com.example.mymushroomf.PembeliAdapter.AddressAdapter;
import com.example.mymushroomf.R;

import java.util.ArrayList;
import java.util.List;

public class AddressListActivity extends AppCompatActivity {
    private TextView addAddress;
    private List<Address> addressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_list);

        // Find the back arrow ImageButton by its ID
        ImageButton backButton = findViewById(R.id.btnback);  // Use the updated ID

        // Set up the back button functionality
        backButton.setOnClickListener(view -> onBackPressed()); // Go back to the previous activity

        // Create a list of addresses
        addressList = new ArrayList<>();
        addressList.add(new Address("Indira S", "087882025909", "Perumahan Bogor, Bogor Utara - Kota Bogor, Jawa Barat", "Rumah"));
        addressList.add(new Address("Salsabil R", "082125547623", "Perumahan Graha Bunga GB 3 No. 12, RT 1/RW 8, Pondok Kacang Barat, Pondok Aren, Tangerang Selatan", "Kos"));
        addressList.add(new Address("Indira S", "087882025909", "Perumahan Bogor, Bogor Utara - Kota Bogor, Jawa Barat", "Kos"));

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_viewaddress);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AddressAdapter adapter = new AddressAdapter(this, addressList);
        recyclerView.setAdapter(adapter);

        // Handle add address button click
        addAddress = findViewById(R.id.add_address);
        addAddress.setOnClickListener(view -> {
            Intent notifIntent = new Intent(AddressListActivity.this, TambahAlamatActivity.class);
            startActivity(notifIntent);
        });
    }
}
