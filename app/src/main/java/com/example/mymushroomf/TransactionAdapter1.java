package com.example.mymushroomf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TransactionAdapter1 extends RecyclerView.Adapter<TransactionAdapter1.TransactionViewHolder> {

    private Context context;
    private List<Transaction1> transactionList;

    public TransactionAdapter1(Context context, List<Transaction1> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Pastikan layout transaction_item.xml sesuai dengan item yang ingin Anda tampilkan
        View view = LayoutInflater.from(context).inflate(R.layout.p1item, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        // Ambil transaksi pada posisi tertentu
        Transaction1 transaction = transactionList.get(position);

        // Set data transaksi pada view holder
        holder.productName.setText(transaction.getName());
        holder.productQuantity.setText(transaction.getType());
        holder.transactionStatus.setText(transaction.getStatus());
        holder.productPrice.setText(transaction.getPrice());

        // Jika Anda menggunakan Glide untuk menampilkan gambar produk
        Glide.with(context)
                .load(transaction.getImageUri()) // URL gambar produk
                .into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    // ViewHolder untuk setiap item transaksi
    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productQuantity, productPrice;
        ImageView productImage;
        Button transactionStatus;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            // Menyambungkan elemen layout dengan variabel
            productName = itemView.findViewById(R.id.product_name);
            productQuantity = itemView.findViewById(R.id.product_quantity);
            transactionStatus = itemView.findViewById(R.id.transaction_status);
            productPrice = itemView.findViewById(R.id.product_price);
            productImage = itemView.findViewById(R.id.product_image);
        }
    }
}