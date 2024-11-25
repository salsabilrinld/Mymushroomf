package com.example.mymushroomf.PembeliAdapter;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mymushroomf.PembeliActivity.Keranjang1Activity;
import com.example.mymushroomf.PembeliModel.CartItem;
import com.example.mymushroomf.PembeliModel.CartManager;
import com.example.mymushroomf.PembeliService.CartService;
import com.example.mymushroomf.R;
import com.example.mymushroomf.ApiClient;
import com.google.gson.Gson;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private Context context;
    private OnCartItemInteractionListener interactionListener;
    private SharedPreferences sharedPreferences;
    private Keranjang1Activity activity;

    public CartAdapter(List<CartItem> cartItems, Context context, OnCartItemInteractionListener interactionListener, SharedPreferences sharedPreferences) {
        this.cartItems = cartItems;
        this.context = context;
        this.interactionListener = interactionListener;
        this.sharedPreferences = context.getSharedPreferences("CartPrefs", MODE_PRIVATE);
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
        holder.itemQuantity.setText(String.valueOf(cartItem.getQuantity()));

        int totalPrice = cartItem.getProduct().getPrice() * cartItem.getQuantity();
        holder.itemPrice.setText(formatCurrency(totalPrice));
        holder.checkBox.setChecked(cartItem.getIsSelected() == 1);

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            cartItem.setIsSelected(isChecked ? 1 : 0);

            saveSelectionToSharedPreferences(cartItem);
            interactionListener.onCartUpdated();
        });

        holder.btnIncrease.setOnClickListener(v -> {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            Log.d("CartAdapter", "Increased quantity: " + cartItem.getQuantity());
            updateCartItemQuantity(cartItem, cartItem.getQuantity());
        });

        holder.btnDecrease.setOnClickListener(v -> {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                updateCartItemQuantity(cartItem, cartItem.getQuantity());
            }
        });

        holder.btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Remove Item")
                    .setMessage("Are you sure you want to remove this item from the cart?")
                    .setPositiveButton("Yes", (dialog, which) -> removeItemFromCart(cartItem.getId(), position))
                    .setNegativeButton("No", null)
                    .show();
        });

        // Memuat gambar dari URL menggunakan Glide
        Glide.with(holder.itemView.getContext())
                .load(cartItem.getProduct().getFile_path())  // Asumsikan ini adalah URL gambar
                .into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }


    public void updateCartItemQuantity(CartItem cartItem, int newQuantity) {
        cartItem.setQuantity(newQuantity);
        notifyDataSetChanged();

        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");


        CartService cartService = ApiClient.getCartService();
        Call<CartItem> call = cartService.updateCart("Bearer" + token, cartItem.getId(), cartItem);

        call.enqueue(new Callback<CartItem>() {
            @Override
            public void onResponse(Call<CartItem> call, Response<CartItem> response) {
                Log.d("UpdateQuantity", "Response code: " + response.code());
                Log.d("UpdateQuantity", "Response body: " + new Gson().toJson(response.body()));
                Log.d("UpdateQuantity", "Response error body: " + (response.errorBody() != null ? response.errorBody().toString() : "No error body"));

                if (response.isSuccessful()) {
                    interactionListener.onCartUpdated();  // Notify the activity to update the total price or UI
                } else {
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartItem> call, Throwable t) {
                // Handle failure
            }
        });
    }


    public void updateCartItems(List<CartItem> newCartItems) {
        this.cartItems.clear();
        this.cartItems = newCartItems;
        notifyDataSetChanged();
    }

    private void removeItemFromCart(int cartItemId, int position) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        CartService cartService = ApiClient.getCartService();
        Call<Void> call = cartService.removeFromCart("Bearer " + token, cartItemId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Remove item from list and notify adapter
                    cartItems.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(context, "Item removed from cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to remove item", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void saveSelectionToSharedPreferences(CartItem cartItem) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("CartPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(String.valueOf(cartItem.getId()), cartItem.isSelected());
        editor.apply();
    }

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

    private String formatCurrency(int amount) {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return format.format(amount).replace("Rp", "Rp. ").replace(",00", "");
    }
}
