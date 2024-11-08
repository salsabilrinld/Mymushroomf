package com.example.mymushroomf;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.example.mymushroomf.databinding.ActivityOrderdetailBinding;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class OrderDetailActivity extends AppCompatActivity {

    private LinearLayout layoutOrderStatus,layoutCourier, layoutTrackingNumber, layoutAddress, layoutProductCost, layoutDeliveryCost, layoutTotalPayment;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail);  // Assuming your layout file name is 'activity_transaction_detail.xml'

        // Initialize views
        layoutOrderStatus = findViewById(R.id.status_order);  // Example, replace with actual IDs
        layoutCourier = findViewById(R.id.kurir_detail);
        layoutTrackingNumber = findViewById(R.id.resi_detail);
        layoutAddress = findViewById(R.id.alamat_detail);
        layoutProductCost = findViewById(R.id.biaya_produk);
        layoutDeliveryCost = findViewById(R.id.biaya_kirim);
        layoutTotalPayment = findViewById(R.id.total_biaya);
        btnBack = findViewById(R.id.back_order);

        TextView txtOrderStatus = layoutOrderStatus.findViewById(R.id.tvstatus_order);  // TextView inside status_order layout
        TextView txtCourier = layoutCourier.findViewById(R.id.tvkurir_detail);  // TextView inside kurir_detail layout
        TextView txtTrackingNumber = layoutTrackingNumber.findViewById(R.id.tvresi_detail);
        TextView txtAddress = layoutAddress.findViewById(R.id.tvalamat_detail);
        TextView txtProductCost = layoutProductCost.findViewById(R.id.tvbiaya_produk);
        TextView txtDeliveryCost = layoutDeliveryCost.findViewById(R.id.tvbiaya_kirim);
        TextView txtTotalPayment = layoutTotalPayment.findViewById(R.id.tvtotal_biaya);

        // Set data dynamically (replace these with your actual data)
        txtOrderStatus.setText("Selesai");  // Set based on your data
        txtCourier.setText("Reguler");
        txtTrackingNumber.setText("TKP01-W5JEM982");
        txtAddress.setText("Indira S\n0878-8202-5909\nPerumahan Bogor, Bogor Utara - Kota Bogor - Jawa Barat, ID 16151");
        txtProductCost.setText("Rp. 12.000");
        txtDeliveryCost.setText("Rp. 7.000");
        txtTotalPayment.setText("Rp. 19.000");

        // Set onClickListener for back button
        btnBack.setOnClickListener(v -> {
            // Handle the back button logic (e.g., finish activity)
            onBackPressed();  // Go back to previous screen
        });
    }
}
