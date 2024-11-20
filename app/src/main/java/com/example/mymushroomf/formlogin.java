package com.example.mymushroomf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mymushroomf.PembeliActivity.Dashboard1Activity;
import com.example.mymushroomf.PembeliModel.Users;
import com.example.mymushroomf.PembeliService.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class formlogin extends AppCompatActivity { // Menggunakan FormLogin seperti yang diinginkan

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
//        } else {
//            Gson gson = new GsonBuilder()
//                    .setLenient()
//                    .create();
//
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("https://192.168.101.85/mushroom4/public/api/")
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build();
//
//            UserService service = retrofit.create(UserService.class);
//            Call<Users> call = service.signin(email, password);
//
//            call.enqueue(new Callback<Users>() {
//                @Override
//                public void onResponse(Call<Users> call, Response<Users> response) {
//                    if (response.isSuccessful() && response.body() != null) {
//                        Users data = response.body();
//
//                        // Login berhasil, arahkan ke MainActivity
//                        Intent intent = new Intent(formlogin.this, Dashboard1Activity.class);
//                        intent.putExtra("email", data.getEmail());
//                        startActivity(intent);
//                        finish();
//                    } else {
//                        // Respons gagal atau pengguna tidak ditemukan
//                        Toast.makeText(getApplicationContext(), "Pengguna tidak terdaftar atau data tidak valid", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Users> call, Throwable t) {
//                    // Log kesalahan dan tampilkan pesan error kepada pengguna
//                    Log.e("LoginError", "Login failed: " + t.getMessage());
//                    Toast.makeText(getApplicationContext(), "Gagal terhubung ke server, coba lagi nanti.", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
        }


        else {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://mushroom.miauwlan.com/api/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            UserService service = retrofit.create(UserService.class);
            Call<Users> call = service.signin(email, password);

            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if (response.isSuccessful() && response.body() != null && "success".equals(response.body().getStatus())) {
                        Users.UserData userData = response.body().getUser(); // Mengambil objek user

                        // Simpan nama pengguna di SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name", userData.getUsername()); // Mendapatkan username dari objek user
                        editor.putString("email", userData.getEmail());
                        editor.putString("phone", userData.getPhone());
                        editor.putString("password", userData.getPassword());
                        editor.putString("profile_path", userData.getProfilePath());
                        editor.apply();

                        // Login berhasil, arahkan ke MainActivity
                        Intent intent = new Intent(formlogin.this, Dashboard1Activity.class);
                        intent.putExtra("email", userData.getEmail());
                        startActivity(intent);
                        finish();
                    } else {
                        // Respons gagal atau pengguna tidak ditemukan
                        Toast.makeText(getApplicationContext(), "Pengguna tidak terdaftar atau data tidak valid", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    // Log kesalahan dan tampilkan pesan error kepada pengguna
                    Log.e("LoginError", "Login failed: " + t.getMessage());
                    Toast.makeText(getApplicationContext(), "Gagal terhubung ke server, coba lagi nanti.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}