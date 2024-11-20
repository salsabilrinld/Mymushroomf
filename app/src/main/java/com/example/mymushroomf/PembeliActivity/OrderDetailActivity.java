package com.example.mymushroomf.PembeliActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mymushroomf.R;

public class OrderDetailActivity extends AppCompatActivity {

    private String orderId;
    private TextView tvKurirDetail, tvResiDetail, tvAlamatDetail, tvNamaPemesan, tvNoTelpPemesan, tvNoResiDetail, tvMetodePembayaran, tvBiayaProduk, tvBiayaKirim, tvTotalBiaya;
    private ImageButton backOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetail); // Set the layout file

        // Initialize the views by finding them by ID
        orderId = getIntent().getStringExtra("orderId");
        tvKurirDetail = findViewById(R.id.tvkurir_detail);
        tvResiDetail = findViewById(R.id.tvresi_detail);
        tvAlamatDetail = findViewById(R.id.tvalamat_detail);
        tvNamaPemesan = findViewById(R.id.tvnama_pemesan);
        tvNoTelpPemesan = findViewById(R.id.tvnomor_pemesan);
        tvNoResiDetail = findViewById(R.id.tvresi_detail);
        tvMetodePembayaran = findViewById(R.id.tvmetode_pembayaran);
        tvBiayaProduk = findViewById(R.id.tvbiaya_produk);
        tvBiayaKirim = findViewById(R.id.tvbiaya_kirim);
        tvTotalBiaya = findViewById(R.id.tvtotal_biaya);
        backOrderButton = findViewById(R.id.back_order);

        // Set data to the TextViews
        tvKurirDetail.setText("Reguler");
        tvResiDetail.setText("TKP01-W5JEM982");
        tvAlamatDetail.setText("Perumahan Bogor, Bogor Utara - Kota Bogor - Jawa Barat, ID 16151");
        tvNamaPemesan.setText("Indira S");
        tvNoTelpPemesan.setText("0878-8202-5909");
        tvMetodePembayaran.setText("BNI Virtual Account");
        tvBiayaProduk.setText("Rp. 12.000");
        tvBiayaKirim.setText("Rp. 7.000");
        tvTotalBiaya.setText("Rp. 19.000");


        // Set up back button click listener
        backOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Example: Go back to previous activity
                finish();
            }
        });
    }
}
