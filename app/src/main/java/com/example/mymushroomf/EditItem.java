package com.example.mymushroomf;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class EditItem extends DialogFragment {

    private ImageView profileImageView;
    private TextView changePictureText;
    private EditText changeName, changePrice, changeDesc;
    private Spinner changeType;
    private TextView changeQuantity;
    private int stockQuantity = 1;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_edit_item, null);

        profileImageView = view.findViewById(R.id.profile_image);
        changePictureText = view.findViewById(R.id.change_picture);
        changeName = view.findViewById(R.id.edit_nama);
        changePrice = view.findViewById(R.id.edit_harga);
        changeDesc = view.findViewById(R.id.edit_desc);
        changeQuantity = view.findViewById(R.id.tv_quantity);

        changePictureText.setOnClickListener(v -> openImagePicker());


        builder.setView(view);
        return builder.create();
    }

    private void openImagePicker() {
        // Implementasikan kode untuk membuka galeri atau kamera di sini
    }
}
