package com.example.mymushroomf;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AddressListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_list);

        List<Address> addressList = new ArrayList<>();
        addressList.add(new Address("Indira S", "087882025909", "Perumahan Bogor, Bogor Utara - Kota Bogor, Jawa Barat", "Rumah"));
        addressList.add(new Address("Salsabil R", "082125547623", "Perumahan Graha Bunga GB 3 No. 12, RT 1/RW 8, Pondok Kacang Barat, Pondok Aren, Tangerang Selatan", "Kos"));
        addressList.add(new Address("Indira S", "087882025909", "Perumahan Bogor, Bogor Utara - Kota Bogor, Jawa Barat", "Kos"));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AddressAdapter adapter = new AddressAdapter(addressList);
        recyclerView.setAdapter(adapter);
    }
}

