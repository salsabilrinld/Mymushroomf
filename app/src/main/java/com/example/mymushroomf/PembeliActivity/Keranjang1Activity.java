package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.PembeliAdapter.CartAdapter;
import com.example.mymushroomf.PembeliModel.CartItem;
import com.example.mymushroomf.PembeliModel.CartManager;
import com.example.mymushroomf.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Keranjang1Activity extends AppCompatActivity implements CartAdapter.OnCartItemInteractionListener {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItems;
    private TextView totalPriceTextView;
    private CheckBox checkBoxSelectAll;
    private Button buttonBeli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang1);

        // Inisialisasi views
        cartRecyclerView = findViewById(R.id.recycler_viewkeranjang);
        totalPriceTextView = findViewById(R.id.totalPrice);
        checkBoxSelectAll = findViewById(R.id.checkBoxSelectAll);
        buttonBeli = findViewById(R.id.btnBuy);

        // Set up RecyclerView
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load cart items dari CartManager
        cartItems = CartManager.getInstance(this).getCartItems();

        // Set adapter untuk RecyclerView
        cartAdapter = new CartAdapter(cartItems, this, this);
        cartRecyclerView.setAdapter(cartAdapter);

        // Setup checkbox "Pilih Semua"
        checkBoxSelectAll.setOnClickListener(v -> {
            boolean isChecked = checkBoxSelectAll.isChecked();
            selectAllItems(isChecked);
            cartAdapter.notifyDataSetChanged();
            updateTotalPrice();
        });

        // Update total harga
        updateTotalPrice();

        // Handle back button
        ImageView backButton = findViewById(R.id.iv_back);
        backButton.setOnClickListener(v -> onBackPressed());

        // Handle tombol Beli
        buttonBeli.setOnClickListener(v -> {
            List<CartItem> selectedItems = getSelectedItems();

            if (selectedItems.isEmpty()) {
                Toast.makeText(this, "Pilih produk terlebih dahulu", Toast.LENGTH_SHORT).show();
            } else {
                // Debug log untuk memastikan data tidak kosong
                for (CartItem item : selectedItems) {
                    System.out.println("Selected Item: " + item.getProduct().getName());
                }

                // Intent ke PemesananDetailActivity
                Intent intent = new Intent(Keranjang1Activity.this, PemesananDetailActivity.class);
                intent.putExtra("selectedItems", (Serializable) selectedItems);
                startActivity(intent);

                // Debug log untuk memastikan Intent dipanggil
                System.out.println("Intent ke PemesananDetailActivity dipanggil");
            }
        });

    }

    // Mendapatkan daftar item yang dipilih
    private List<CartItem> getSelectedItems() {
        List<CartItem> selectedItems = new ArrayList<>();
        for (CartItem item : cartItems) {
            if (item.isSelected()) {
                selectedItems.add(item);
            }
        }
        return selectedItems;
    }

    // Update total harga
    private void updateTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            // Hitung hanya item yang dipilih
            if (item.isSelected()) {
                total += item.getProduct().getPrice() * item.getQuantity();
            }
        }
        totalPriceTextView.setText("Total: Rp. " + total);
    }

    // Pilih semua item di keranjang
    private void selectAllItems(boolean select) {
        for (CartItem item : cartItems) {
            item.setSelected(select);
        }
    }

    @Override
    public void onCartUpdated() {
        updateTotalPrice();
    }

    // Menambahkan item ke dalam keranjang
    public void addItemToCart(CartItem cartItem) {
        boolean itemExists = CartManager.getInstance(this).isProductInCart(cartItem.getProduct());

        // Jika item belum ada di keranjang, tambahkan ke cart
        if (!itemExists) {
            CartManager.getInstance(this).addItem(cartItem);
        } else {
            // Jika item sudah ada, tambah kuantitasnya
            for (CartItem item : cartItems) {
                if (item.getProduct().equals(cartItem.getProduct())) {
                    item.setQuantity(item.getQuantity() + cartItem.getQuantity());
                    break;
                }
            }
        }

        // Menyimpan perubahan ke CartManager dan perbarui RecyclerView
        cartAdapter.notifyDataSetChanged();
        updateTotalPrice();
    }
}
