package com.example.mymushroomf.PembeliActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymushroomf.R;

public class  PemesananDetailActivity extends AppCompatActivity {

    private ImageView ivBack;
    private Button btnChangeAddress;
    private Button btnPlaceOrder;
    private TextView tvTotalPayment, tvShippingCost, quantityText, tvProductCost;
    private RadioGroup radioGroupShipping;
    private RadioButton rbRegular, rbNextDay;

    private int productPrice = 12000; // Example product price
    private int shippingCost = 7000;  // Default shipping cost (Regular)
    private int productCost = 12000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanandetail); // Ensure this matches your XML file name

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

        // Mendapatkan data yang dikirim dari Popup
        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");
        int quantity = intent.getIntExtra("quantity", 1);
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
                // Open address change activity
                Intent intent = new Intent(PemesananDetailActivity.this, AddressListActivity.class);
                startActivity(intent);
            }
        });

        // Place Order button click listener
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle placing the order
                Toast.makeText(PemesananDetailActivity.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();
                // Optionally, navigate to another activity or close this one
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

}
