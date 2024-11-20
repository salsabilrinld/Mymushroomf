package com.example.mymushroomf.PembeliActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mymushroomf.PembeliModel.Order;
import com.example.mymushroomf.PembeliModel.OrderDetail;
import com.example.mymushroomf.PembeliModel.Produk1;
import com.example.mymushroomf.R;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderDetailActivity extends AppCompatActivity {

    private List<Order> orderList;
    private String orderId;
    private TextView productName, tvStatus, tvKurirDetail, tvResiDetail, tvAlamatDetail, tvNamaPemesan, tvNoTelpPemesan, tvMetodePembayaran, tvBiayaProduk, tvBiayaKirim, tvTotalBiaya, tvQuantity, tvTanggalOrder;
    private ImageButton backOrderButton;
    private ImageView productImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail); // Set the layout file
//
        Order order = (Order) getIntent().getSerializableExtra("order");  // Retrieve the Order object

        backOrderButton = findViewById(R.id.back_order);

        productName = findViewById(R.id.product_name);
        productImage = findViewById(R.id.product_image);
        tvStatus = findViewById(R.id.tvstatus_order);
        tvKurirDetail = findViewById(R.id.tvkurir_detail);
        tvResiDetail = findViewById(R.id.tvresi_detail);
        tvAlamatDetail = findViewById(R.id.tvalamat_detail);
        tvNamaPemesan = findViewById(R.id.tvnama_pemesan);
        tvNoTelpPemesan = findViewById(R.id.tvnomor_pemesan);
        tvMetodePembayaran = findViewById(R.id.tvmetode_pembayaran);
        tvQuantity = findViewById(R.id.product_quantity);
        tvBiayaProduk = findViewById(R.id.tvbiaya_produk);
        tvBiayaKirim = findViewById(R.id.tvbiaya_kirim);
        tvTotalBiaya = findViewById(R.id.tvtotal_biaya);
        tvTanggalOrder = findViewById(R.id.tvtanggal_kirim);

        if (order != null) {
            // Now you have the complete Order object and can use its details
            OrderDetail orderDetail = order.getOrderDetails().get(0);  // Get the first order detail


        productName.setText(orderDetail.getProduct().getName());
        productImage.setImageResource(orderDetail.getProduct().getImageResId());
        tvStatus.setText(orderDetail.getStatus());
        tvQuantity.setText(String.valueOf(orderDetail.getQuantity()) + " kg");
        tvKurirDetail.setText(orderDetail.getShippingMethod());
        tvResiDetail.setText(orderDetail.getTrackingNumber());
        tvAlamatDetail.setText(orderDetail.getAddress());
        tvNamaPemesan.setText(orderDetail.getRecipientName());
        tvNoTelpPemesan.setText(orderDetail.getRecipientPhone());
        tvMetodePembayaran.setText(orderDetail.getPaymentMethod());
        tvBiayaProduk.setText(formatCurrency(orderDetail.getProductCost()));
        tvBiayaKirim.setText(formatCurrency(orderDetail.getShippingCost()));
        tvTotalBiaya.setText(formatCurrency(orderDetail.getTotalPayment()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  // Format the date to your desired format
        String formattedDate = sdf.format(orderDetail.getOrderDate());  // Format the orderDate
        tvTanggalOrder.setText("Order Date: " + formattedDate);

        } else {
            Log.e("OrderDetailActivity", "Order is null");
        }



        // Set up back button click listener
        backOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to previous activity
            }
        });
    }

    private Order getOrderById(String orderId) {

//            OrderDetail orderDetail = new OrderDetail("d1", new Produk1("1", "Jamur Tiram", "Deskripsi", "Jamur Mentah", "100 kg", 12000, R.drawable.jamur_tiram),
//                    "Selesai", " JNE", "123", "Bogor", "Indira", "0821", new Date(), 1, 12000, 7000, 19000);
//            ArrayList<OrderDetail> orderDetails = new ArrayList<>();
//            orderDetails.add(orderDetail);
//
//            return new Order(orderId, orderDetails);
        for (Order order : orderList) { // Assuming you have a list of orders
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null; // Return null if no matching order is found
    }

    private String formatCurrency(int amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return format.format(amount).replace("Rp", "Rp. ").replace(",00", "");
    }

}
