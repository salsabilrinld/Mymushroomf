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
import com.example.mymushroomf.PembeliModel.Address;
import com.example.mymushroomf.PembeliModel.CartItem;
import com.example.mymushroomf.PembeliModel.Notifications;
import com.example.mymushroomf.PembeliModel.NotificationsStorage;
import com.example.mymushroomf.R;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class PemesananDetailActivity extends AppCompatActivity {

    private ImageView ivBack;
    private Button btnChangeAddress, btnPlaceOrder;
    private TextView tvTotalPayment, tvShippingCost, quantityText, tvProductCost;
    private RadioGroup radioGroupShipping;
    private RadioButton rbRegular, rbNextDay;
    private ArrayList<CartItem> cartItems;
    private CartItem popupItem;
    private TextView tvAddressLabel, tvAddressNamePhone, tvAddressDetails;

    private Address selectedAddress;
    private int shippingCost = 7000; // Default shipping cost (Regular)
    private int totalPrice = 0; // Total price calculated dynamically
    private int productCost = 12000; // Example product price

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanandetail);
        Log.d("DEBUG", "PemesananDetailActivity berhasil dibuka");

        ivBack = findViewById(R.id.iv_back);
        btnChangeAddress = findViewById(R.id.btn_change_address);
        btnPlaceOrder = findViewById(R.id.btn_place_order);
        tvShippingCost = findViewById(R.id.tv_shipping_cost);
        tvProductCost = findViewById(R.id.tv_product_cost);
        tvTotalPayment = findViewById(R.id.tv_total_payment);
        tvAddressLabel = findViewById(R.id.tv_address_label);
        tvAddressNamePhone = findViewById(R.id.tv_address_name_phone);
        tvAddressDetails = findViewById(R.id.tv_address_details);
        quantityText = findViewById(R.id.quantityText);
        radioGroupShipping = findViewById(R.id.radio_group_shipping);
        rbRegular = findViewById(R.id.rb_regular);
        rbNextDay = findViewById(R.id.rb_next_day);

        Intent intent = getIntent();
        cartItems = (ArrayList<CartItem>) intent.getSerializableExtra("cartData");
        popupItem = (CartItem) intent.getSerializableExtra("popupData");

        if (cartItems != null && !cartItems.isEmpty()) {
            displayCartItems();
        } else if (popupItem != null) {
            displayPopupItem();
        }

        totalPrice = intent.getIntExtra("totalPrice", productCost);
        quantityText.setText(formatCurrency(totalPrice));
        tvProductCost.setText("Biaya Produk: " + formatCurrency(totalPrice));

        ivBack.setOnClickListener(v -> finish());

        btnChangeAddress.setOnClickListener(v -> {
            Intent addressIntent = new Intent(PemesananDetailActivity.this, AddressListActivity.class);
            startActivityForResult(addressIntent, 1);
        });

        btnPlaceOrder.setOnClickListener(v -> {
            if (selectedAddress == null) {
                Toast.makeText(PemesananDetailActivity.this, "Pilih alamat terlebih dahulu", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(PemesananDetailActivity.this, "Pesanan Berhasil Dibuat", Toast.LENGTH_SHORT).show();
            String title = "Pesanan";
            String message = "Pesanan Anda berhasil dibuat!";
            String timestamp = java.text.DateFormat.getDateTimeInstance().format(new java.util.Date());
            Notifications notificationsList = new Notifications(title, message, timestamp);
            NotificationsStorage.saveNotifications(PemesananDetailActivity.this, notificationsList);
            finish();
        });

        radioGroupShipping.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_regular) {
                shippingCost = 7000;
            } else if (checkedId == R.id.rb_next_day) {
                shippingCost = 14000;
            }

            tvShippingCost.setText("Biaya Pengiriman :" + formatCurrency(shippingCost));
            int totalPayment = totalPrice + shippingCost;
            tvTotalPayment.setText("Total Pembayaran: " + formatCurrency(totalPayment));
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            selectedAddress = (Address) data.getSerializableExtra("selectedAddress");

            Log.d("SelectedAddress", "Name: " + selectedAddress.getName());
            Log.d("SelectedAddress", "Phone: " + selectedAddress.getPhoneNumber());
            Log.d("SelectedAddress", "Address: " + selectedAddress.getAddress());

            if (selectedAddress != null) {
                tvAddressLabel.setText(selectedAddress.getType());
                tvAddressNamePhone.setText(selectedAddress.getName() + " (" + selectedAddress.getPhoneNumber() + ")");
                tvAddressDetails.setText(selectedAddress.getAddress());
            }
        }
    }


    private String formatCurrency(int amount) {
        Locale locale = new Locale("id", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        return format.format(amount).replace("Rp", "Rp. ").replace(",00", "");
    }

    private void displayCartItems() {
        RelativeLayout containerProducts = findViewById(R.id.container_product);
        int previousViewId = 0;
        for (CartItem item : cartItems) {
            TextView productView = new TextView(this);
            productView.setId(View.generateViewId());
            productView.setText(item.getProduct().getName() + " - Qty: " + item.getQuantity() +
                    " - Price: " + formatCurrency(item.getProduct().getPrice() * item.getQuantity()));
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
                " - Price: " + formatCurrency(popupItem.getProduct().getPrice() * popupItem.getQuantity()));
        productView.setTextSize(16);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        productView.setLayoutParams(params);
        containerProducts.addView(productView);
    }
}