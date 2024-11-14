package com.example.mymushroomf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        // Inflate the layout for each item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.produk1, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Produk1 product = productList.get(position);
        holder.productImage.setImageResource(product.getImageResId());
        holder.productName.setText(product.getName());
        holder.productWeight.setText(product.getWeight());
        holder.productPrice.setText(product.getPrice());

        holder.buyButton.setOnClickListener(v ->
                Toast.makeText(context, "Membeli " + product.getName(), Toast.LENGTH_SHORT).show()
        );

        holder.deleteButton.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION) { // Ensure valid position
                productList.remove(currentPosition);
                notifyItemRemoved(currentPosition);
                Toast.makeText(context, "Produk " + product.getName() + " dihapus dari favorit", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productWeight;
        TextView productPrice;
        Button buyButton;
        ImageButton deleteButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);

            productPrice = itemView.findViewById(R.id.product_price);


        }
    }
}