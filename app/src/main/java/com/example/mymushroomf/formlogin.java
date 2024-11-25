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

import com.example.mymushroomf.PembeliActivity.DashboardActivity;
import com.example.mymushroomf.PembeliModel.Users;
import com.example.mymushroomf.PembeliService.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class formlogin extends AppCompatActivity { // Menggunakan FormLogin seperti yang diinginkan

    private UserService userService;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView createAccountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formlogin);

//        userService = ApiClient.getRetrofitInstance(getApplicationContext()).create(UserService.class);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        createAccountText = findViewById(R.id.createAccountText);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        loginButton.setOnClickListener(v -> loginUser());

        createAccountText.setOnClickListener(v -> {
            Intent intent = new Intent(formlogin.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(formlogin.this, "Email dan Password harus diisi", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Gson gson = new GsonBuilder().setLenient().create();
            UserService service = ApiClient.getUserService();
            Call<Users> call = service.login(email, password);

            // Retrofit enqueue call to handle API request asynchronously
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    Log.d("Login", "Response: " + response.toString());
                    Log.d("Login", "Response user: " + response.body().getData());
                    if (response.isSuccessful() && response.body() != null) {

                        // Log the response body to check its content
                        Log.d("Login", "Response body: " + new Gson().toJson(response.body()));

                        // Log the status to verify its value
                        Log.d("Login", "Response status: " + response.body().getStatus());


                        Users apiResponse = response.body();

                        // Access data
                        Users.Data data = apiResponse.getData();
                        String token = data.getToken();
                        Users.User user = data.getUser();


                        // Save data to SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("token", token);
                        editor.putInt("userId", user.getId());
                        editor.putString("fullname", user.getFullname());
                        editor.putString("username", user.getUsername());
                        editor.putString("email", user.getEmail());
                        editor.putString("phone", user.getPhone());
                        editor.putString("address", user.getAddress());
                        editor.putString("profilePath", user.getProfilePath());

                        // Apply changes
                        editor.apply();

                        Log.d("PREFS", "Data saved to SharedPreferences");

                        // Now navigate to the Dashboard or other activity
                        Intent intent = new Intent(formlogin.this, DashboardActivity.class);
                        startActivity(intent);
                        finish(); // Optional: Close the login activity
                    } else {
                        Log.e("API_ERROR", "Response unsuccessful");
                        Toast.makeText(formlogin.this, "Login failed. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    Log.e("API_ERROR", "Request failed: " + t.getMessage());
                    Toast.makeText(formlogin.this, "Network error. Please try again.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}