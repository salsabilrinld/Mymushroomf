package com.example.mymushroomf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile1Activity extends AppCompatActivity {

    private ImageView profileImage;
    private TextView username, followInfo;
    private Button editProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile1);

        profileImage = findViewById(R.id.profileImage);
        username = findViewById(R.id.username);
        followInfo = findViewById(R.id.followInfo);
        editProfileButton = findViewById(R.id.editProfileButton);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        LinearLayout transaksiButton = findViewById(R.id.dtransaksi);
        LinearLayout penilaianButton = findViewById(R.id.bpenilaian);
        LinearLayout logoutButton = findViewById(R.id.logout1);

        editProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(Profile1Activity.this, EditProfile1Activity.class);
            startActivity(intent);
        });

        transaksiButton.setOnClickListener(v -> {
            Intent intent = new Intent(Profile1Activity.this, TransactionListActivity1.class);
            startActivity(intent);
        });

        penilaianButton.setOnClickListener(v -> {
            Intent intent = new Intent(Profile1Activity.this, ReviewActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            Toast.makeText(Profile1Activity.this, "Logging out...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Profile1Activity.this, formlogin.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

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

        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        String role = sharedPreferences.getString("role", "");

        if ("Pembeli".equals(role)) {
            setupPembeliProfile();
        } else if ("Penjual".equals(role)) {
            setupPenjualProfile();
        }
    }

    private void setupPembeliProfile() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        String imageUriString = sharedPreferences.getString("profileImageUri", null);

        if (imageUriString != null) {
            Uri profileImageUri = Uri.parse(imageUriString);
            profileImage.setImageURI(profileImageUri);
        } else {
            profileImage.setImageResource(R.drawable.ic_profile);
        }

        username.setText("Username Pembeli");
        followInfo.setText("Followers: 100");
    }

    private void setupPenjualProfile() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        String imageUriString = sharedPreferences.getString("profileImageUri", null);

        if (imageUriString != null) {
            Uri profileImageUri = Uri.parse(imageUriString);
            profileImage.setImageURI(profileImageUri);
        } else {
            profileImage.setImageResource(R.drawable.ic_profile);
        }

        username.setText("Username Penjual");
        followInfo.setText("Followers: 200");
    }
}