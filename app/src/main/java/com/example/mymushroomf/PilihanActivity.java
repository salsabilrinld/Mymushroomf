package com.example.mymushroomf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class PilihanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan);

        // Menangani klik untuk Penjual
        LinearLayout penjualLayout = findViewById(R.id.penjual_layout);  // ID untuk layout Penjual
        penjualLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke aktivitas login dan mengirimkan role Penjual
                Intent intent = new Intent(PilihanActivity.this, formlogin.class);
                intent.putExtra("role", "Penjual");  // Menyimpan data role sebagai Penjual
                startActivity(intent);
            }
        });

        // Menangani klik untuk Pembeli
        LinearLayout pembeliLayout = findViewById(R.id.pembeli_layout);  // ID untuk layout Pembeli
        pembeliLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke aktivitas login dan mengirimkan role Pembeli
                Intent intent = new Intent(PilihanActivity.this, formlogin.class);
                intent.putExtra("role", "Pembeli");  // Menyimpan data role sebagai Pembeli
                startActivity(intent);
            }
        });
    }
}