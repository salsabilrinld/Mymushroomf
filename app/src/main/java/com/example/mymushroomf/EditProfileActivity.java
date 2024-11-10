package com.example.mymushroomf;

import static com.example.mymushroomf.AddProductActivity.PICK_IMAGE_REQUEST;
import static com.example.mymushroomf.AddProductActivity.STORAGE_PERMISSION_CODE;
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
        cancelButton = findViewById(R.id.cancel_profile); // Initialize cancel button

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String savedName = sharedPreferences.getString("name", ""); // Default to empty string if not found
        String savedEmail = sharedPreferences.getString("email", "");
        String savedNumber = sharedPreferences.getString("number", "");
        String savedPassword = sharedPreferences.getString("password", "");

        // Set the retrieved values in the EditText fields
        nameEditText.setText(savedName);
        emailEditText.setText(savedEmail);
        numberEditText.setText(savedNumber);
        passwordEditText.setText(savedPassword);

        // Change profile picture
        changeProfilePicButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                // Permission is already granted, proceed with your logic
                openGallery();
            } else {
                // Request permission if not granted
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION_CODE);
            }
        });


        updateButton.setOnClickListener(view -> {
            String updatedName = nameEditText.getText().toString();
            String updatedEmail = emailEditText.getText().toString();
            String updatedPassword = passwordEditText.getText().toString();

            // Memastikan semua input terisi sebelum memperbarui data
            if (!updatedName.isEmpty() && !updatedEmail.isEmpty() && !updatedPassword.isEmpty()) {
                SharedPreferences UserSharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = UserSharedPreferences.edit();

                // Menyimpan data terbaru ke SharedPreferences
                editor.putString("name", updatedName);
                editor.putString("email", updatedEmail);
                editor.putString("password", updatedPassword);
                editor.apply();

                Toast.makeText(EditProfileActivity.this, "Profile updated", Toast.LENGTH_SHORT).show();

                // Kembali ke ProfileActivity setelah pembaruan berhasil
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(EditProfileActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            }
        });


        // Cancel editing and close the activity without saving
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
            profileImageView.setImageURI(imageUri); // Display selected image in ImageView
        }
    }

    private void updateProfile() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String number = numberEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Validate inputs
        if (name.isEmpty() || email.isEmpty() || number.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("number", number);
        editor.putString("password", password);
        editor.apply(); // Apply changes asynchronously

        // Notify the user and close the activity
        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with the logic
                openGallery();
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
