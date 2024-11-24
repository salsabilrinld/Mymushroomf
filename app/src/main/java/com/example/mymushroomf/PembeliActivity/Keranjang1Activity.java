package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.ApiClient;
import com.example.mymushroomf.PembeliAdapter.CartAdapter;
import com.example.mymushroomf.PembeliModel.CartItem;
import com.example.mymushroomf.PembeliService.CartService;
import com.example.mymushroomf.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        cartRecyclerView = findViewById(R.id.recycler_viewkeranjang);
        totalPriceTextView = findViewById(R.id.totalPrice);
        checkBoxSelectAll = findViewById(R.id.checkBoxSelectAll);
        buttonBeli = findViewById(R.id.btnBuy);


        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartItems = new ArrayList<>();

        SharedPreferences sharedPreferences = getSharedPreferences("CartPrefs", MODE_PRIVATE);

        cartAdapter = new CartAdapter(cartItems, this, this, sharedPreferences);
        cartRecyclerView.setAdapter(cartAdapter);

        fetchCartItems();

        checkBoxSelectAll.setOnClickListener(v -> {
            boolean isChecked = checkBoxSelectAll.isChecked();
            selectAllItems(isChecked);
            cartAdapter.notifyDataSetChanged();
            updateTotalPrice();
        });

        updateTotalPrice();

        ImageView backButton = findViewById(R.id.iv_back);
        backButton.setOnClickListener(v -> onBackPressed());

        buttonBeli.setOnClickListener(v -> {
            List<CartItem> selectedItems = getSelectedItems();

            if (selectedItems.isEmpty()) {
                Toast.makeText(this, "Pilih produk terlebih dahulu", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(Keranjang1Activity.this, PemesananDetailActivity.class);

                int totalPrice = calculateTotalPrice(selectedItems);
                intent.putExtra("totalPrice", totalPrice);

                startActivity(intent);
            }
        });

    }



    private void fetchCartItems() {

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        if (token.isEmpty()) {

            return;
        }

        CartService cartService = ApiClient.getCartService();
        Call<List<CartItem>> call = cartService.viewCart("Bearer " + token);

        call.enqueue(new Callback<List<CartItem>>() {
            @Override
            public void onResponse(Call<List<CartItem>> call, Response<List<CartItem>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        cartItems.clear();
                        cartItems = response.body();
                        cartAdapter.updateCartItems(cartItems);
                    } else {
                        Log.e("DashboardActivity", "Response body is null");
                        Toast.makeText(Keranjang1Activity.this, "Response body is empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Keranjang1Activity.this, "Failed to load cart", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<CartItem>> call, Throwable t) {
                Toast.makeText(Keranjang1Activity.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }


    private int calculateTotalPrice(List<CartItem> selectedItems) {
        int total = 0;
        for (CartItem item : selectedItems) {
            total += item.getProduct().getPrice() * item.getQuantity(); // Calculate total based on selected items
        }
        return total;
    }


    // Get selected items from the cart
    private List<CartItem> getSelectedItems() {
        List<CartItem> selectedItems = new ArrayList<>();
        for (CartItem item : cartItems) {
            if (item.getIsSelected() == 1) {
                selectedItems.add(item);
            }
        }
        return selectedItems;
    }


    private void updateTotalPrice() {
        int total = 0;
        for (CartItem item : cartItems) {
            if (item.getIsSelected() == 1) { // Only count selected items
                total += item.getProduct().getPrice() * item.getQuantity();
            }
        }
        totalPriceTextView.setText("Total: " + formatCurrency(total));
    }

    private void selectAllItems(boolean select) {
        SharedPreferences sharedPreferences = getSharedPreferences("CartPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (CartItem item : cartItems) {
            item.setIsSelected(select ? 1 : 0);
            // Save selection state for each item
            editor.putInt("selected_cart_item_" + item.getId(), item.getIsSelected());
        }
        editor.apply();
    }

    @Override
    public void onCartUpdated() {
        updateTotalPrice(); // Update total price when the cart is updated
    }




    private String formatCurrency(int amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return format.format(amount).replace("Rp", "Rp. ").replace(",00", "");
    }
}

