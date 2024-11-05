package com.example.mymushroomf;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private List<Product> productList;
    private OnProductClickListener listener;

    public ProductAdapter(Context context, List<Product> productList, OnProductClickListener listener) {
        this.context = context;
        this.productList = productList;
        this.listener = listener;
    }

    public interface OnProductClickListener {
        void onProductClick(Product product);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productNameTextView.setText(product.getName());
        holder.productTypeTextView.setText(product.getType());
        holder.productPriceTextView.setText(String.valueOf(product.getPrice()));

        // Load the image using Glide
        Glide.with(holder.itemView.getContext())
                .load(product.getImageResId()) // Use the image path or URI
                .into(holder.productImageView); // Set the ImageView in your ViewHolder
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;
        TextView productNameTextView;
        TextView productPriceTextView;
        TextView productTypeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.product_image);
            productNameTextView = itemView.findViewById(R.id.product_name);
            productTypeTextView = itemView.findViewById(R.id.product_item);
            productPriceTextView = itemView.findViewById(R.id.product_price);
        }

        public void bind(Product product, OnProductClickListener listener) {
            productNameTextView.setText(product.getName()); // Assuming you have a getName() method in Product
            productTypeTextView.setText(product.getType()); // Assuming you have a getDescription() method
            Uri ImageUriConverted = Uri.parse(product.getImageResId());
            productImageView.setImageURI(ImageUriConverted); // Assuming you have an appropriate method for getting the image resource ID

            itemView.setOnClickListener(v -> listener.onProductClick(product));
        }
    }
}
