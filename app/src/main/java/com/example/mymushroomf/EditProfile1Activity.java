package com.example.mymushroomf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfile1Activity extends AppCompatActivity {

    private EditText usernameEditText, emailEditText, phoneEditText, passwordEditText;
    private Button cancelButton, updateButton;
    private ImageView profileImageView;
    private SharedPreferences sharedPreferences;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri profileImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileedit1);

        sharedPreferences = getSharedPreferences("UserProfile", MODE_PRIVATE);

        usernameEditText = findViewById(R.id.change_username);
        emailEditText = findViewById(R.id.change_email);
        phoneEditText = findViewById(R.id.change_number);
        passwordEditText = findViewById(R.id.change_password);
        cancelButton = findViewById(R.id.cancel_profile);
        updateButton = findViewById(R.id.update_profile);
        profileImageView = findViewById(R.id.profileImage);

        loadUserData();

        profileImageView.setOnClickListener(v -> openImageSelector());
        cancelButton.setOnClickListener(v -> finish());
        updateButton.setOnClickListener(v -> updateProfile());
    }

    private void openImageSelector() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            profileImageUri = data.getData();
            profileImageView.setImageURI(profileImageUri);
        }
    }

    private void loadUserData() {
        String username = sharedPreferences.getString("username", "Nama Default");
        String email = sharedPreferences.getString("email", "default@gmail.com");
        String phone = sharedPreferences.getString("phone", "0000000000");
        String password = sharedPreferences.getString("password", "");

        usernameEditText.setText(username);
        emailEditText.setText(email);
        phoneEditText.setText(phone);
        passwordEditText.setText(password);

        String imageUriString = sharedPreferences.getString("profileImageUri", null);
        if (imageUriString != null) {
            profileImageUri = Uri.parse(imageUriString);
            profileImageView.setImageURI(profileImageUri);
        }
    }

    private void updateProfile() {
        String username = usernameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("email", email);
        editor.putString("phone", phone);
        editor.putString("password", password);

        if (profileImageUri != null) {
            editor.putString("profileImageUri", profileImageUri.toString());
        }

        editor.apply();
        Toast.makeText(this, "Profil berhasil diperbarui!", Toast.LENGTH_SHORT).show();
    }
}