package com.example.mymushroomf.PembeliAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.PembeliActivity.Keranjang1Activity;
import com.example.mymushroomf.PembeliActivity.ProductDetailActivity;
import com.example.mymushroomf.PembeliModel.CartItem;
import com.example.mymushroomf.PembeliModel.CartManager;
import com.example.mymushroomf.PembeliModel.Produk1;
import com.example.mymushroomf.PembeliActivity.Popup;
import com.example.mymushroomf.R;

import java.util.List;

public class ProdukAdapterPembeli extends RecyclerView.Adapter<ProdukAdapterPembeli.ProductViewHolder> {

    private List<Produk1> productList;
    private Context context;

    public ProdukAdapterPembeli(Context context, List<Produk1> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout for each product item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.produk1, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Produk1 product = productList.get(position);
        holder.productImage.setImageResource(product.getImageResId());
        holder.productName.setText(product.getName());
        holder.productCategory.setText(product.getType());
        holder.productPrice.setText("Rp. " + product.getPrice());

        // Set OnClickListener for the ImageView to go to Product Detail Activity
        holder.productImage.setOnClickListener(v -> {
            // Intent to go to the ProductDetailActivity
            Intent intent = new Intent(context, ProductDetailActivity.class);

            // Send product data through Intent
            intent.putExtra("productName", product.getName());
            intent.putExtra("productDescription", product.getDesc());
            intent.putExtra("productPrice", product.getPrice());
            intent.putExtra("productImage", product.getImageResId());

            // Start the ProductDetailActivity
            context.startActivity(intent);
        });

        // Set OnClickListener for the "add to cart" button to show the popup
        holder.addButton.setOnClickListener(v -> {
            // Show the product details in a popup when the button is clicked
//            new Popup(context, product.getName(), product.getImageResId(), product.getPrice());
            CartItem cartItem = new CartItem(product, 1); // Tambahkan produk ke keranjang

            // Menambahkan item ke keranjang melalui CartManager
            CartManager.getInstance(context).addItem(cartItem);

            // Beri notifikasi kepada user
            Toast.makeText(context, "Produk ditambahkan ke keranjang", Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    // Method to update the product list and refresh the RecyclerView
    public void updateProductList(List<Produk1> filteredProducts) {
        this.productList = filteredProducts;  // Update the product list
        notifyDataSetChanged(); // Notify the adapter to refresh the RecyclerView
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productCategory;
        TextView productPrice;
        ImageButton addButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productCategory = itemView.findViewById(R.id.productCategory);
            addButton = itemView.findViewById(R.id.addToCartButton);  // The button that triggers the popup
        }
    }
}
