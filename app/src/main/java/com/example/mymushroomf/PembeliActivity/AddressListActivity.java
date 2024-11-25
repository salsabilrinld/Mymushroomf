package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button selectAddressButton;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_list);

        // Find the back arrow ImageButton by its ID
        ImageButton backButton = findViewById(R.id.btnback);  // Use the updated ID
        backButton.setOnClickListener(view -> onBackPressed());

        // Create a list of addresses
        addressList = new ArrayList<>();
        addressList.add(new Address("Indira S", "087882025909", "Perumahan Bogor, Bogor Utara - Kota Bogor, Jawa Barat", "Rumah"));
        addressList.add(new Address("Salsabil R", "082125547623", "Perumahan Graha Bunga GB 3 No. 12, RT 1/RW 8, Pondok Kacang Barat, Pondok Aren, Tangerang Selatan", "Kos"));
        addressList.add(new Address("Indira S", "087882025909", "Perumahan Bogor, Bogor Utara - Kota Bogor, Jawa Barat", "Kos"));

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_viewaddress);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AddressAdapter adapter = new AddressAdapter(this, addressList, new AddressAdapter.OnAddressSelectedListener() {
            @Override
            public void onAddressSelected(int position) {
                selectedPosition = position;  // Update selectedPosition when an address is clicked
            }
        });
        recyclerView.setAdapter(adapter);

        // Handle add address button click
        addAddress = findViewById(R.id.add_address);
        addAddress.setOnClickListener(view -> {
            Intent notifIntent = new Intent(AddressListActivity.this, TambahAlamatActivity.class);
            startActivity(notifIntent);
        });

        selectAddressButton = findViewById(R.id.selectButton);
        selectAddressButton.setOnClickListener(view -> {
            if (selectedPosition != -1) {
                Address selectedAddress = addressList.get(selectedPosition);  // Ambil alamat yang dipilih
                Intent resultIntent = new Intent();
                resultIntent.putExtra("selectedAddress", selectedAddress);
                setResult(RESULT_OK, resultIntent);
                finish();  // Kembali ke PemesananDetailActivity
            } else {
                Toast.makeText(AddressListActivity.this, "Pilih alamat terlebih dahulu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}