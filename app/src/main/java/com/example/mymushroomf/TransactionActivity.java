package com.example.mymushroomf;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class TransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);

        TextView shippingMethodButton = findViewById(R.id.shipping_method);
        shippingMethodButton.setOnClickListener(v -> showBottomSheetDialog());
    }

    private void showBottomSheetDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_shipping_method, null);

        view.findViewById(R.id.reguler_option).setOnClickListener(v -> {
            // Handle regular shipping selection
            dialog.dismiss();
        });

        view.findViewById(R.id.nextday_option).setOnClickListener(v -> {
            // Handle regular shipping selection
            dialog.dismiss();
        });

        view.findViewById(R.id.cargo_option).setOnClickListener(v -> {
            // Handle regular shipping selection
            dialog.dismiss();
        });

        view.findViewById(R.id.economic_option).setOnClickListener(v -> {
            // Handle regular shipping selection
            dialog.dismiss();
        });

        dialog.setContentView(view);
        dialog.show();
    }
}

