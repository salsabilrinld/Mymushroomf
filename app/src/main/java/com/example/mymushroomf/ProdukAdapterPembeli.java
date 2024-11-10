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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Produk1> productList;
    private Context context;

    public ProductAdapter(Context context, List<Produk1> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Pastikan file item_produk1.xml ada di res/layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk1, parent, false);
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
            int currentPosition = holder.getAbsoluteAdapterPosition();
            productList.remove(currentPosition);
            notifyItemRemoved(currentPosition);
            notifyItemRangeChanged(currentPosition, productList.size());
            Toast.makeText(context, "Produk " + product.getName() + " dihapus dari favorit", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productWeight, productPrice;
        Button buyButton;
        ImageButton deleteButton;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productWeight = itemView.findViewById(R.id.product_weight);
            productPrice = itemView.findViewById(R.id.product_price);
            buyButton = itemView.findViewById(R.id.button_buy);
            deleteButton = itemView.findViewById(R.id.button_delete);
        }
    }
}
