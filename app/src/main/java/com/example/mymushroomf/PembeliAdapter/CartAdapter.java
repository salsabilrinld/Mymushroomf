package com.example.mymushroomf.PembeliAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mymushroomf.PembeliModel.CartItem;
import com.example.mymushroomf.PembeliModel.CartManager;
import com.example.mymushroomf.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private Context context;
    private OnCartItemInteractionListener interactionListener;

    public CartAdapter(List<CartItem> cartItems, Context context, OnCartItemInteractionListener interactionListener) {
        this.cartItems = cartItems;
        this.context = context;
        this.interactionListener = interactionListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_keranjang, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);

        holder.itemName.setText(cartItem.getProduct().getProduct_name());
        holder.itemPrice.setText("Rp. " + cartItem.getProduct().getPrice());
        holder.itemQuantity.setText(String.valueOf(cartItem.getQuantity()));
        holder.checkBox.setChecked(cartItem.isSelected());

        // Mengubah status seleksi ketika checkbox dicentang
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            cartItem.setSelected(isChecked);
            CartManager.getInstance(context).saveCart();  // Menyimpan perubahan ke SharedPreferences
            interactionListener.onCartUpdated();
        });

        // Tombol tambah jumlah produk
        holder.btnIncrease.setOnClickListener(v -> {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            CartManager.getInstance(context).saveCart();  // Menyimpan perubahan ke SharedPreferences
            notifyItemChanged(position);
            interactionListener.onCartUpdated();
        });

        // Tombol kurangi jumlah produk
        holder.btnDecrease.setOnClickListener(v -> {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                CartManager.getInstance(context).saveCart();  // Menyimpan perubahan ke SharedPreferences
                notifyItemChanged(position);
                interactionListener.onCartUpdated();
            }
        });

        // Tombol hapus item dari keranjang
        holder.btnDelete.setOnClickListener(v -> {
            cartItems.remove(position);
            CartManager.getInstance(context).saveCart();  // Menyimpan perubahan ke SharedPreferences
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartItems.size());
            interactionListener.onCartUpdated();
        });

        // Memuat gambar dari URL menggunakan Glide
        Glide.with(holder.itemView.getContext())
                .load(cartItem.getProduct().getFile_path())  // Asumsikan ini adalah URL gambar
//                .placeholder(R.drawable.placeholder_image)      // Gambar sementara saat loading
//                .error(R.drawable.error_image)                  // Gambar error jika gagal memuat
                .into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public void updateCartItems(List<CartItem> updatedCartItems) {
        this.cartItems = updatedCartItems;
        notifyDataSetChanged();
    }

    // ViewHolder untuk item keranjang
    public static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, itemPrice, itemQuantity;
        CheckBox checkBox;
        ImageView itemImage, btnIncrease, btnDecrease, btnDelete;

        public CartViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemQuantity = itemView.findViewById(R.id.itemQuantity);
            checkBox = itemView.findViewById(R.id.checkBox);
            itemImage = itemView.findViewById(R.id.image);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    // Interface untuk interaksi dengan item keranjang
    public interface OnCartItemInteractionListener {
        void onCartUpdated();
    }
}
