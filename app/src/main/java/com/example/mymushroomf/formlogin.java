package com.example.mymushroomf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class formlogin extends AppCompatActivity { // Menggunakan formlogin seperti yang diinginkan

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView createAccountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formlogin);

        // Menghubungkan layout dengan kode
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        createAccountText = findViewById(R.id.createAccountText);

        // Set padding untuk Edge-to-Edge UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Logika untuk tombol login
        loginButton.setOnClickListener(v -> loginUser());

        // Logika untuk teks pendaftaran
        createAccountText.setOnClickListener(v -> {
            // Arahkan ke aktivitas pendaftaran
            Intent intent = new Intent(formlogin.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();


        // Periksa apakah email dan password diisi
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(formlogin.this, "Email dan Password harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mengambil data dari SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String storedEmail = sharedPreferences.getString("email", null);
        String storedPassword = sharedPreferences.getString("password", null);
        String storedName = sharedPreferences.getString("name", ""); // Ambil nama pengguna


        Log.d("LoginDebug", "Stored Email: " + storedEmail + ", Stored Password: " + storedPassword);
        Log.d("LoginDebug", "Input Email: " + email + ", Input Password: " + password);


        // Memeriksa apakah email dan password yang dimasukkan sama dengan yang tersimpan
        if (email.equals(storedEmail) && password.equals(storedPassword)) {
            // Login berhasil
            Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show();

            // Simpan nama pengguna untuk digunakan di DashboardActivity
            SharedPreferences myPrefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = myPrefs.edit();
            editor.putString("name", storedName); // Menyimpan nama pengguna
            editor.apply();

            // Pindah ke DashboardActivity
            Intent intent = new Intent(formlogin.this, DashboardActivity.class);
            startActivity(intent);
            finish(); // Mengakhiri formlogin
        } else {
            Toast.makeText(this, "Login gagal, cek email dan password", Toast.LENGTH_SHORT).show();
        }
    }
}