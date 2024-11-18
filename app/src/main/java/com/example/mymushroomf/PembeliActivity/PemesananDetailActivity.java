package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymushroomf.PembeliModel.CartItem;
import com.example.mymushroomf.PembeliModel.Notifications;
import com.example.mymushroomf.PembeliModel.NotificationsStorage;
import com.example.mymushroomf.R;

import java.util.ArrayList;

public class  PemesananDetailActivity extends AppCompatActivity {

    private ImageView ivBack;
    private Button btnChangeAddress;
    private Button btnPlaceOrder;
    private TextView tvTotalPayment, tvShippingCost, quantityText, tvProductCost;
    private RadioGroup radioGroupShipping;
    private RadioButton rbRegular, rbNextDay;
    private ArrayList<CartItem> cartItems;
    private CartItem popupItem;

    private int productPrice = 12000; // Example product price
    private int shippingCost = 7000;  // Default shipping cost (Regular)
    private int productCost = 12000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanandetail);
        Log.d("DEBUG", "PemesananDetailActivity berhasil dibuka");// Ensure this matches your XML file name

        ivBack = findViewById(R.id.iv_back);
        btnChangeAddress = findViewById(R.id.btn_change_address);
        btnPlaceOrder = findViewById(R.id.btn_place_order);
        tvShippingCost = findViewById(R.id.tv_shipping_cost);
        tvProductCost = findViewById(R.id.tv_product_cost);
        tvTotalPayment = findViewById(R.id.tv_total_payment);
        quantityText = findViewById(R.id.quantityText);
        radioGroupShipping = findViewById(R.id.radio_group_shipping);
        rbRegular = findViewById(R.id.rb_regular);
        rbNextDay = findViewById(R.id.rb_next_day);

        RelativeLayout containerProducts = findViewById(R.id.container_product);
        cartItems = getIntent().getParcelableArrayListExtra("cartData");

        if (getIntent().hasExtra("popupData")) {
            popupItem = getIntent().getParcelableExtra("popupData");
        }

        if (cartItems != null && !cartItems.isEmpty()) {
            displayCartItems();
        } else if (popupItem != null) {
            displayPopupItem();
        } else {
        }


        // Mendapatkan data yang dikirim dari Popup
        Intent intent = getIntent();
//        String productName = intent.getStringExtra("productName");
//        int quantity = intent.getIntExtra("quantity", 1);
        int totalPrice = intent.getIntExtra("totalPrice", 0); // Default ke harga produk jika tidak ada data


        quantityText.setText("Rp. " + totalPrice);
        tvProductCost.setText("Biaya Produk: Rp. " + totalPrice);


        // Back button click listener
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity
            }
        });

        // Change Address button click listener
        btnChangeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PemesananDetailActivity", "Ubah Alamat tombol diklik");
                // Open address change activity
                Intent intent = new Intent(PemesananDetailActivity.this, TambahAlamatActivity.class);
                startActivity(intent);
            }
        });

        // Place Order button click listener
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle order placement
                Toast.makeText(PemesananDetailActivity.this, "Pesanan Berhasil Dibuat", Toast.LENGTH_SHORT).show();

                // Save notification
                String title = "Pesanan";
                String message = "Pesanan Anda berhasil dibuat!";
                String timestamp = java.text.DateFormat.getDateTimeInstance().format(new java.util.Date());

                Notifications notificationsList = new Notifications(title, message, timestamp);
                NotificationsStorage.saveNotifications(PemesananDetailActivity.this, notificationsList);

                // Optionally navigate to another activity or finish this one
                finish();
            }
        });

        // RadioGroup for Shipping Method
        radioGroupShipping.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_regular) {
                    shippingCost = 7000; // Regular shipping cost
                } else if (checkedId == R.id.rb_next_day) {
                    shippingCost = 14000; // Next Day shipping cost
                }

                tvShippingCost.setText("Biaya Pengiriman : Rp. " + shippingCost);
                int totalPayment = totalPrice + shippingCost;
                tvTotalPayment.setText("Total Pembayaran: Rp. " + totalPayment);

            }
        });

    }

    private void displayCartItems() {
        RelativeLayout containerProducts = findViewById(R.id.container_product);

        int previousViewId = 0;
        for (CartItem item : cartItems) {
            TextView productView = new TextView(this);
            productView.setId(View.generateViewId());
            productView.setText(item.getProduct().getName() + " - Qty: " + item.getQuantity() +
                    " - Price: Rp. " + (item.getProduct().getPrice() * item.getQuantity()));
            productView.setTextSize(16);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );

            if (previousViewId != 0) {
                params.addRule(RelativeLayout.BELOW, previousViewId);
            }

            productView.setLayoutParams(params);
            containerProducts.addView(productView);

            previousViewId = productView.getId();
        }
    }
    private void displayPopupItem() {
        RelativeLayout containerProducts = findViewById(R.id.container_product);

        TextView productView = new TextView(this);
        productView.setId(View.generateViewId());
        productView.setText(popupItem.getProduct().getName() + " - Qty: " + popupItem.getQuantity() +
                " - Price: Rp. " + (popupItem.getProduct().getPrice() * popupItem.getQuantity()));
        productView.setTextSize(16);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        productView.setLayoutParams(params);
        containerProducts.addView(productView);
    }


}