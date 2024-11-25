package com.example.mymushroomf.PembeliActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymushroomf.R;

public class TambahAlamatActivity extends AppCompatActivity {

    private EditText etDetailAlamat, etProvinsi, etKota, etKecamatan, etKelurahan, etRT, etRW, etKodePos;
    private Button btnTambahAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahalamat);

        // Initialize views
        ImageView backIcon = findViewById(R.id.backIcon);
        etDetailAlamat = findViewById(R.id.etDetailAlamat);
        etProvinsi = findViewById(R.id.etProvinsi);
        etKota = findViewById(R.id.etKota);
        etKecamatan = findViewById(R.id.etKecamatan);
        etKelurahan = findViewById(R.id.etKelurahan);
        etRT = findViewById(R.id.etRT);
        etRW = findViewById(R.id.etRW);
        etKodePos = findViewById(R.id.etKodePos);
        btnTambahAlamat = findViewById(R.id.btnTambahAlamat);

        // Set up back button functionality
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity and return to the previous screen
            }
        });

        // Set up add address button functionality
        btnTambahAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get input values
                String detailAlamat = etDetailAlamat.getText().toString().trim();
                String provinsi = etProvinsi.getText().toString().trim();
                String kota = etKota.getText().toString().trim();
                String kecamatan = etKecamatan.getText().toString().trim();
                String kelurahan = etKelurahan.getText().toString().trim();
                String rt = etRT.getText().toString().trim();
                String rw = etRW.getText().toString().trim();
                String kodePos = etKodePos.getText().toString().trim();

                // Perform action with data (e.g., save to database, display message)
                Toast.makeText(TambahAlamatActivity.this, "Alamat berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
