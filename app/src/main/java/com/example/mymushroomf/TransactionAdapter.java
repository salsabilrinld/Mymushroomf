package com.example.mymushroomf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private Context context;
    private List<Transaction> transactionList;

    public TransactionAdapter(Context context, List<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transaction, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transaction transaction = transactionList.get(position);

        holder.productName.setText(transaction.getFungiName());
        holder.productQuantity.setText(transaction.getFungiQuantity());
        holder.transactionStatus.setText(transaction.getTransactionStatus());
        holder.productTotal.setText("Total Belanja");
        holder.productPrice.setText(transaction.getFungiPrice());

        // Jika Anda menggunakan Glide untuk menampilkan gambar produk
        Glide.with(context)
                .load(transaction.getFungiImageUrl()) // URL gambar produk
                .into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productQuantity, productTotal, productPrice;
        ImageView productImage;
        Button transactionStatus;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productQuantity = itemView.findViewById(R.id.product_quantity);
            transactionStatus = itemView.findViewById(R.id.transaction_status);
            productTotal = itemView.findViewById(R.id.product_total);
            productPrice = itemView.findViewById(R.id.product_price);
            productImage = itemView.findViewById(R.id.product_image);
        }
    }
}

