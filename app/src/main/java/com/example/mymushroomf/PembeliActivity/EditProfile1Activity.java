package com.example.mymushroomf.PembeliActivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mymushroomf.PembeliModel.Users;
import com.example.mymushroomf.PembeliModel.Users;
import com.example.mymushroomf.PembeliService.UserService;
import com.example.mymushroomf.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfile1Activity extends AppCompatActivity {
    private ImageView profileImageView;
    private EditText nameEditText, emailEditText, passwordEditText, numberEditText;
    private Button changeProfilePicButton, updateButton, cancelButton;
    private static final int PICK_IMAGE_REQUEST = 2;
    private static final int STORAGE_PERMISSION_CODE = 1;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileedit1);

        profileImageView = findViewById(R.id.profileImage);
        nameEditText = findViewById(R.id.change_username);
        emailEditText = findViewById(R.id.change_email);
        numberEditText = findViewById(R.id.change_number);
        passwordEditText = findViewById(R.id.change_password);
        changeProfilePicButton = findViewById(R.id.change_picture);
        updateButton = findViewById(R.id.update_profile);
        cancelButton = findViewById(R.id.cancel_profile);

        // Load existing profile data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String savedName = sharedPreferences.getString("name", "");
        String savedEmail = sharedPreferences.getString("email", "");
        String savedNumber = sharedPreferences.getString("phone", "");
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
                openGallery();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION_CODE);
            }
        });

        // Update profile information
        updateButton.setOnClickListener(view -> {
            String updatedName = nameEditText.getText().toString().trim();
            String updatedEmail = emailEditText.getText().toString().trim();
            String updatedNumber = numberEditText.getText().toString().trim();
            String updatedPassword = passwordEditText.getText().toString().trim();

//            if (!updatedName.isEmpty() && !updatedEmail.isEmpty()) {
                // Create a UserData object
                Users.Data userData = new Users.Data();

                // Create a User object and set the updated values
                Users.User user = new Users.User();
                user.setUsername(updatedName);
                user.setEmail(updatedEmail);
                user.setPhone(updatedNumber);
                user.setPassword(updatedPassword);

                // Set the user object in userData
                userData.setUser(user);

                // Log the updated values
                Log.d("UserData", "Updated Name: " + userData.getUser().getUsername());
                Log.d("UserData", "Updated Email: " + userData.getUser().getEmail());
//            }

            // Call API to update profile
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://mushroom.miauwlan.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            UserService apiService = retrofit.create(UserService.class);
            Call<Users> call = apiService.updateProfile(userData);

            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Users.Data updatedUser = response.body().getData();
                        Toast.makeText(EditProfile1Activity.this, "Profile updated", Toast.LENGTH_SHORT).show();

                        // Save updated data to SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name", updatedUser.getUser().getUsername());
                        editor.putString("email", updatedUser.getUser().getEmail());
                        editor.putString("phone", updatedUser.getUser().getPhone());
                        editor.putString("password", updatedPassword); // Save password if changed
                        editor.apply();

                        // Navigate back to ProfileActivity after successful update
                        Intent intent = new Intent(EditProfile1Activity.this, Profile1Activity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(EditProfile1Activity.this, "Update failed: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {
                    Toast.makeText(EditProfile1Activity.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Cancel editing and close the activity without saving
        cancelButton.setOnClickListener(v -> finish());
    }

    // Open the gallery to choose a profile picture
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Handle the result of picking an image from the gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                imageUri = selectedImageUri;
                profileImageView.setImageURI(imageUri); // Display selected image in ImageView
            } else {
                Toast.makeText(this, "Failed to select image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permission denied. Unable to change profile picture.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
