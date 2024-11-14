package com.example.mymushroomf;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Produk1FavoriteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProdukAdapterPembeli adapter; // Use the correct adapzter here
    private List<Produk1> favoriteProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk1_favorite); // Ensure correct layout resource

        recyclerView = findViewById(R.id.recycler_myproduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        favoriteProducts = getFavoriteProductList();
        adapter = new ProdukAdapterPembeli(this, favoriteProducts); // Use the correct adapter here
        recyclerView.setAdapter(adapter);
    }

    private List<Produk1> getFavoriteProductList() {
        List<Produk1> products = new ArrayList<>();
        products.add(new Produk1("Jamur Tiram", "1 kg", "Rp. 12.000", R.drawable.jamur_tiram));
        products.add(new Produk1("Jamur Shitake", "1 kg", "Rp. 30.000", R.drawable.jamur_shitake));
        products.add(new Produk1("Jamur Kuping", "1 kg", "Rp. 30.000", R.drawable.jamur_kuping));
        products.add(new Produk1("Jamur Enoki", "1 kg", "Rp. 30.000", R.drawable.jamur_enoki));
        return products;
    }
}