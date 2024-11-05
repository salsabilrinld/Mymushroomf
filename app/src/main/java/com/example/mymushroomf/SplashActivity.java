package com.example.mymushroomf;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash); // Menghubungkan ke layout splash

        // Timer untuk menampilkan splash screen selama 3 detik
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Pindah ke FormLoginActivity setelah splash screen
                Intent intent = new Intent(SplashActivity.this, formlogin.class);
                startActivity(intent);
                finish(); // Mengakhiri SplashActivity agar tidak bisa kembali
            }
        }, 3000); // 3000 ms = 3 detik
    }
}
