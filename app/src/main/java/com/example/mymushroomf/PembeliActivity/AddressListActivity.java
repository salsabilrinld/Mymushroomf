package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.Address;
import com.example.mymushroomf.AddressAdapter;
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

        List<Address> addressList = new ArrayList<>();
        addressList.add(new Address("Indira S", "087882025909", "Perumahan Bogor, Bogor Utara - Kota Bogor, Jawa Barat", "Rumah"));
        addressList.add(new Address("Salsabil R", "082125547623", "Perumahan Graha Bunga GB 3 No. 12, RT 1/RW 8, Pondok Kacang Barat, Pondok Aren, Tangerang Selatan", "Kos"));
        addressList.add(new Address("Indira S", "087882025909", "Perumahan Bogor, Bogor Utara - Kota Bogor, Jawa Barat", "Kos"));

        RecyclerView recyclerView = findViewById(R.id.recycler_viewaddress);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AddressAdapter adapter = new AddressAdapter(this, addressList);
        recyclerView.setAdapter(adapter);

        TextView addAddress = findViewById(R.id.add_address);

        addAddress.setOnClickListener(view -> {
            Intent notifIntent = new Intent(AddressListActivity.this, TambahAlamatActivity.class);
            startActivity(notifIntent);
        });
    }
}

