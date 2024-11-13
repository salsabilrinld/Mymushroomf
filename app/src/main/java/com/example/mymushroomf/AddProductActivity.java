package com.example.mymushroomf;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class AddProductActivity extends AppCompatActivity {

    private EditText fungiNameEditText, fungiPriceEditText, fungiDescriptionEditText;
    private Spinner fungiTypeSpinner;
    private Button saveButton, cancelButton, uploadPhotoButton;
    public static final int PICK_IMAGE_REQUEST = 1;
    public static final int STORAGE_PERMISSION_CODE = 100;
    private TextView quantityTextView;
    private int stockQuantity = 1;
    private Uri imageUri; // To store the selected image URI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        fungiNameEditText = findViewById(R.id.fungi_name);
        fungiPriceEditText = findViewById(R.id.fungi_price);
        fungiDescriptionEditText = findViewById(R.id.fungi_description);
        fungiTypeSpinner = findViewById(R.id.fungi_type);
        uploadPhotoButton = findViewById(R.id.upload_photo_button);
        quantityTextView = findViewById(R.id.tv_quantity);

        String[] fungiTypes = {"Organik", "Nonorganik", "Inorganik"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, fungiTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fungiTypeSpinner.setAdapter(adapter);

        quantityTextView.setText(String.valueOf(stockQuantity));

        findViewById(R.id.btn_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stockQuantity++;
                quantityTextView.setText(String.valueOf(stockQuantity));
            }
        });

        findViewById(R.id.btn_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stockQuantity > 1) { // Prevent stock quantity from going below 1
                    stockQuantity--;
                    quantityTextView.setText(String.valueOf(stockQuantity));
                } else {
                    Toast.makeText(AddProductActivity.this, "Stok tidak bisa kurang dari 1", Toast.LENGTH_SHORT).show();
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        uploadPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(AddProductActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    openFileManager();
                } else {
                    ActivityCompat.requestPermissions(AddProductActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openFileManager();
            } else {
                Toast.makeText(this, "Permission denied to access storage", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openFileManager() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*"); // Restrict to image files
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData(); // Save the selected image URI
            ImageView imageView = findViewById(R.id.fungi_image);
            imageView.setImageURI(imageUri); // Display the selected image
        }
    }

    private void saveProduct() {
        String fungiName = fungiNameEditText.getText().toString().trim();
        String fungiPrice = fungiPriceEditText.getText().toString().trim();
        String fungiDescription = fungiDescriptionEditText.getText().toString().trim();
        String fungiType = fungiTypeSpinner.getSelectedItem().toString();

        if (fungiName.isEmpty() || fungiPrice.isEmpty() || fungiDescription.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (imageUri == null) {
            Toast.makeText(this, "Harap pilih gambar!", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("MyMushroomF", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("fungiName", fungiName);
        editor.putString("fungiPrice", fungiPrice);
        editor.putString("fungiDescription", fungiDescription);
        editor.putString("fungiType", fungiType);
        editor.putString("imageUri", imageUri.toString()); // Save the image URI as a string
        editor.apply();

        String message = "Nama: " + fungiName + "\nTipe: " + fungiType + "\nHarga: " + fungiPrice + "\nDeskripsi: " + fungiDescription;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        showSuccessDialog();
    }

    private void showSuccessDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_successful, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);

        LinearLayout dialogAlert = dialogView.findViewById(R.id.success_dialog);

        dialogAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddProductActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void clearForm() {
        fungiNameEditText.setText("");
        fungiPriceEditText.setText("");
        fungiDescriptionEditText.setText("");
        fungiTypeSpinner.setSelection(0);
        ((ImageView) findViewById(R.id.fungi_image)).setImageURI(null); // Clear the image
        imageUri = null; // Clear the URI
        Toast.makeText(this, "Form direset", Toast.LENGTH_SHORT).show();
    }
}
