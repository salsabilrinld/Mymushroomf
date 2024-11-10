package com.example.mymushroomf;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView profileImageView;
    private EditText nameEditText, emailEditText, passwordEditText, numberEditText;
    private Button changeProfilePicButton, updateButton, cancelButton;
    private static final int PICK_IMAGE_REQUEST = 2;
    private static final int STORAGE_PERMISSION_CODE = 1;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileedit);

        profileImageView = findViewById(R.id.profileImage);
        nameEditText = findViewById(R.id.change_username);
        emailEditText = findViewById(R.id.change_email);
        numberEditText = findViewById(R.id.change_number);
        passwordEditText = findViewById(R.id.change_password);
        changeProfilePicButton = findViewById(R.id.change_picture);
        updateButton = findViewById(R.id.update_profile);
        cancelButton = findViewById(R.id.cancel_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        String savedName = sharedPreferences.getString("name", "");
        String savedEmail = sharedPreferences.getString("email", "");
        String savedNumber = sharedPreferences.getString("number", "");
        String savedPassword = sharedPreferences.getString("password", "");

        nameEditText.setText(savedName);
        emailEditText.setText(savedEmail);
        numberEditText.setText(savedNumber);
        passwordEditText.setText(savedPassword);

        changeProfilePicButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION_CODE);
            }
        });

        updateButton.setOnClickListener(v -> updateProfile());
        cancelButton.setOnClickListener(v -> finish());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            profileImageView.setImageURI(imageUri);
        }
    }

    private void updateProfile() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String number = numberEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (name.isEmpty() || email.isEmpty() || number.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("number", number);
        editor.putString("password", password);
        editor.apply();

        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
