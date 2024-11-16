package com.example.mymushroomf.PembeliAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.PembeliModel.CartItem;
import com.example.mymushroomf.PembeliModel.Produk1;
import com.example.mymushroomf.Product;
import com.example.mymushroomf.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private Context context;
    private OnCartItemInteractionListener interactionListener;

    public CartAdapter(List<CartItem> cartItems, Context context, OnCartItemInteractionListener listener) {
        this.cartItems = cartItems;
        this.context = context;
        this.interactionListener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_keranjang1, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        Produk1 product = cartItem.getProduct();

        // Set product details
        holder.itemName.setText(product.getName());
        holder.itemPrice.setText("Rp. " + product.getPrice());
        holder.itemQuantity.setText(String.valueOf(cartItem.getQuantity()));
        holder.itemImage.setImageResource(R.drawable.jamur_tiram); // Replace with a real image loader if needed.

        // Increase quantity button
        holder.btnIncrease.setOnClickListener(v -> {
            if (product.getStock() > 0) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                product.setStock(product.getStock() - 1);
                notifyItemChanged(position);
                interactionListener.onCartUpdated();
            } else {
                Toast.makeText(context, "Out of stock!", Toast.LENGTH_SHORT).show();
            }
        });

        // Decrease quantity button
        holder.btnDecrease.setOnClickListener(v -> {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                product.setStock(product.getStock() + 1);
                notifyItemChanged(position);
                interactionListener.onCartUpdated();
            } else {
                Toast.makeText(context, "Quantity can't be less than 1!", Toast.LENGTH_SHORT).show();
            }
        });

        // Remove item button
        holder.btnDelete.setOnClickListener(v -> {
            cartItems.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size());
            interactionListener.onCartUpdated();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice, itemQuantity;
        ImageView itemImage;
        ImageButton btnIncrease, btnDecrease, btnDelete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemQuantity = itemView.findViewById(R.id.itemQuantity);
            itemImage = itemView.findViewById(R.id.itemImage);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    public interface OnCartItemInteractionListener {
        void onCartUpdated();
    }
}

