package com.example.mymushroomf;

import static java.lang.CharSequence.compare;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mymushroomf.PembeliActivity.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private List<Product> productList;
    private List<Product> originalList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.originalList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    public void filterByType(String type) {
        if (type.equals("All")) {
            productList = new ArrayList<>(originalList); // show all products
        } else {
            productList = originalList.stream()
                    .filter(product -> product.getType().equals(type))
                    .collect(Collectors.toList());
        }
        notifyDataSetChanged();
    }

    public void sortByPrice(boolean ascending) {
        productList.sort((p1, p2) -> {
            if (ascending) {
                return compare(p1.getPrice(), p2.getPrice());
            } else {
                return compare(p2.getPrice(), p1.getPrice());
            }
        });
        notifyDataSetChanged();
    }

    public void sortByName(boolean ascending) {
        productList.sort((p1, p2) -> {
            if (ascending) {
                return p1.getName().compareToIgnoreCase(p2.getName());
            } else {
                return p2.getName().compareToIgnoreCase(p1.getName());
            }
        });
        notifyDataSetChanged();
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productNameTextView.setText(product.getName());
        holder.productTypeTextView.setText(product.getType());
        holder.productPriceTextView.setText(product.getPrice());

        // Load the image using Glide
        Glide.with(holder.itemView.getContext())
                .load(product.getImageResource()) // Use the image path or URI
                .into(holder.productImageView);
//
//        holder.editButton.setOnClickListener(v -> showEditDialog(position));
//        holder.deleteButton.setOnClickListener(v -> showDeleteDialog(position));
//        holder.itemView.setOnClickListener(v -> openProductDetail(product));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, OrderDetailActivity.class);
            intent.putExtra("PRODUCT_NAME", product.getName());
            intent.putExtra("PRODUCT_TYPE", product.getType());
            intent.putExtra("PRODUCT_PRICE", product.getPrice());
            intent.putExtra("PRODUCT_IMAGE", product.getImageResource());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {

        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;
        TextView productNameTextView, productPriceTextView, productTypeTextView;
        ImageButton editButton, deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.product_image);
            productNameTextView = itemView.findViewById(R.id.product_name);
            productTypeTextView = itemView.findViewById(R.id.product_item);
            productPriceTextView = itemView.findViewById(R.id.product_price);
            editButton = itemView.findViewById(R.id.edit_product);
            deleteButton = itemView.findViewById(R.id.delete_product);


            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Product product = productList.get(position);
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("product_id", product.getId());
                    context.startActivity(intent);
                }
            });

            editButton.setOnClickListener(v -> showEditDialog(getAdapterPosition()));

            deleteButton.setOnClickListener(v -> showDeleteDialog(getAdapterPosition()));
        }

        void bind(Product product) {
            productNameTextView.setText(product.getName());
            productTypeTextView.setText(product.getType());
            productPriceTextView.setText(product.getPrice());

            Glide.with(itemView.getContext())
                    .load(product.getImageResource()) // Use the image path or URI
                    .into(productImageView);
        }

        private void showEditDialog(int position) {
            Product product = productList.get(position);

            // Inflate the dialog layout
            View dialogView = LayoutInflater.from(context).inflate(R.layout.fragment_edit_item, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(dialogView);

            EditText editName = dialogView.findViewById(R.id.edit_nama);
            EditText editPrice = dialogView.findViewById(R.id.edit_harga);
            EditText editDescription = dialogView.findViewById(R.id.edit_desc);
            Spinner editTypeSpinner = dialogView.findViewById(R.id.edit_tipe);

            // Set existing product details in the dialog
            editName.setText(product.getName());
            editPrice.setText(product.getPrice());
            editDescription.setText(product.getDesc());

            // Populate and set the spinner value
            String[] productTypes = {"Organik", "Nonorganik", "Inorganik"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, productTypes);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            editTypeSpinner.setAdapter(adapter);
            editTypeSpinner.setSelection(adapter.getPosition(product.getType())); // Set current type

            builder.setPositiveButton("Save", (dialog, which) -> {
                // Get updated values
                String updatedName = editName.getText().toString().trim();
                String updatedPrice = editPrice.getText().toString().trim();
                String updatedDescription = editDescription.getText().toString().trim();
                String updatedType = editTypeSpinner.getSelectedItem().toString();

                // Validate input if needed
                if (updatedName.isEmpty() || updatedPrice.isEmpty() || updatedDescription.isEmpty()) {
                    Toast.makeText(context, "All fields are required!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Update product data
                product.setName(updatedName);
                product.setPrice(updatedPrice);
                product.setDesc(updatedDescription);
                product.setType(updatedType);

                // Notify adapter about the change
                notifyItemChanged(position);
                Toast.makeText(context, "Product updated successfully", Toast.LENGTH_SHORT).show();
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

            // Show the dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }


        private void showDeleteDialog(int position) {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Product")
                    .setMessage("Are you sure you want to delete this product?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        productList.remove(position);
                        notifyItemRemoved(position);
                        Toast.makeText(context, "Product deleted", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        }

    }
}
