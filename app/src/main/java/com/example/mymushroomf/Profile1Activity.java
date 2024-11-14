package com.example.mymushroomf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile1Activity extends AppCompatActivity {

    private TextView nameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_profile), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameTextView = findViewById(R.id.username);

        loadProfileData();

        ImageButton ratingButton = findViewById(R.id.button_rating);
        ImageButton transactionButton = findViewById(R.id.button_transaction);
        ImageButton logoutButton = findViewById(R.id.button_logout);
        ImageButton notificationsButton = findViewById(R.id.button_notifications);
        ImageButton keranjangButton = findViewById(R.id.button_keranjang);
        Button editButton = findViewById(R.id.editProfileButton);
        logoutButton.setOnClickListener(v -> logoutUser());



        ratingButton.setOnClickListener(view -> {
            Intent ratingIntent = new Intent(Profile1Activity.this, ReviewActivity.class);
            startActivity(ratingIntent);
        });

        transactionButton.setOnClickListener(v -> {
            Intent transactionIntent = new Intent(Profile1Activity.this, TransactionListActivity1.class);
            startActivity(transactionIntent);
        });


        editButton.setOnClickListener(view -> {
            Intent editIntent = new Intent(Profile1Activity.this, EditProfile1Activity.class);
            startActivity(editIntent);
        });

        notificationsButton.setOnClickListener(view -> {
            Intent notifIntent = new Intent(Profile1Activity.this, NotificationsActivity.class);
            startActivity(notifIntent);
        });

        keranjangButton.setOnClickListener(view -> {
            Intent keranjangIntent = new Intent(Profile1Activity.this, Keranjang1Activity.class);
            startActivity(keranjangIntent);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.menu_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_store) {
                    Intent intent = new Intent(Profile1Activity.this, Dashboard1Activity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_transaction) {
                    Intent intent = new Intent(Profile1Activity.this, TransactionListActivity1.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_profile) {
                    Toast.makeText(Profile1Activity.this, "Profile selected", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload profile information every time the activity is resumed
        loadProfileData();
    }

    private void loadProfileData() {
        // Load updated profile info from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "Default Name");

        nameTextView.setText(name);
    }

    private void logoutUser() {

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("isLoggedIn");
        editor.apply();

        Toast.makeText(Profile1Activity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(Profile1Activity.this, formlogin.class);
        startActivity(intent);
        finish(); //
    }
}