package com.example.mymushroomf;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailActivity extends AppCompatActivity {

    private LinearLayout statusOrderLayout, productInfoLayout, courierDetailLayout, resiDetailLayout,
            addressDetailLayout, paymentMethodLayout, productCostLayout, deliveryCostLayout, totalCostLayout;

    private TextView tvStatusOrder, tvProductInfo, tvCourierDetail, tvResiDetail, tvAddressDetail,
            tvPaymentMethod, tvProductCost, tvDeliveryCost, tvTotalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail);

        // Initialize views
        statusOrderLayout = findViewById(R.id.status_order);
        productInfoLayout = findViewById(R.id.info_product);
        courierDetailLayout = findViewById(R.id.kurir_detail);
        resiDetailLayout = findViewById(R.id.resi_detail);
        addressDetailLayout = findViewById(R.id.alamat_detail);
        paymentMethodLayout = findViewById(R.id.metode_pembayaran);
        productCostLayout = findViewById(R.id.biaya_produk);
        deliveryCostLayout = findViewById(R.id.biaya_kirim);
        totalCostLayout = findViewById(R.id.total_biaya);

        tvStatusOrder = statusOrderLayout.findViewById(R.id.tvstatus_order);
        tvProductInfo = productInfoLayout.findViewById(R.id.product_name);
        tvCourierDetail = courierDetailLayout.findViewById(R.id.tvkurir_detail);
        tvResiDetail = resiDetailLayout.findViewById(R.id.tvresi_detail);
        tvAddressDetail = addressDetailLayout.findViewById(R.id.tvalamat_detail);
        tvPaymentMethod = paymentMethodLayout.findViewById(R.id.tvmetode_pembayaran);
        tvProductCost = productCostLayout.findViewById(R.id.tvbiaya_produk);
        tvDeliveryCost = deliveryCostLayout.findViewById(R.id.tvbiaya_kirim);
        tvTotalCost = totalCostLayout.findViewById(R.id.tvtotal_biaya);

        // Check if an Order object or intent extras are passed
        Order order = (Order) getIntent().getSerializableExtra("orderDetail");

        if (order != null) {
            // Populate fields using Order object
            tvStatusOrder.setText(order.getOrderStatus());
            tvProductInfo.setText(order.getProductInfo());
            tvCourierDetail.setText(order.getOrderCourier());
            tvResiDetail.setText(order.getOrderResi());
            tvAddressDetail.setText(order.getOrderAddress());
            tvPaymentMethod.setText(order.getOrderMethod());
            tvProductCost.setText(order.getOrderCostProduct());
            tvDeliveryCost.setText(order.getOrderCostDelivery());
            tvTotalCost.setText(String.valueOf(order.getOrderTotal()));

        } else {
            // Get data passed as individual extras from Intent
            String productName = getIntent().getStringExtra("productName");
            int quantity = getIntent().getIntExtra("quantity", 1);
            int totalPrice = getIntent().getIntExtra("totalPrice", 0);

            // Set data to TextViews
            if (productName != null) {
                tvProductInfo.setText(productName);
            }
            tvProductCost.setText("Rp. " + (totalPrice / quantity));
            tvDeliveryCost.setText("Rp. 0"); // Set delivery cost if applicable
            tvTotalCost.setText("Rp. " + totalPrice);
        }
    }
}
