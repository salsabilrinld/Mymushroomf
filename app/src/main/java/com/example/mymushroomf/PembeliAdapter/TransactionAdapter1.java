package com.example.mymushroomf.PembeliAdapter;

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
import com.example.mymushroomf.PembeliModel.Transaction1;
import com.example.mymushroomf.R;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter1 extends RecyclerView.Adapter<TransactionAdapter1.TransactionViewHolder> {

    private Context context;
    private List<Transaction1> allTransactionList;
    private List<Transaction1> filteredTransactionList;

    public TransactionAdapter1(Context context, List<Transaction1> transactionList) {
        this.context = context;
        this.allTransactionList = transactionList;
        this.filteredTransactionList = new ArrayList<>(transactionList);
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transaction, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {

        Transaction1 transaction = filteredTransactionList.get(position);

        holder.productName.setText(transaction.getName());
        holder.productQuantity.setText(transaction.getType());
        holder.transactionStatus.setText(transaction.getStatus());
        holder.productPrice.setText(transaction.getPrice());

        Glide.with(context)
                .load(transaction.getImageUri())
                .into(holder.productImage);
    }

    @Override
    public int getItemCount() {
        return filteredTransactionList.size();
    }

    public void filter(String status) {
        filteredTransactionList.clear();
        if (status.equals("semua")) {
            filteredTransactionList.addAll(allTransactionList);
        } else {
            for (Transaction1 transaction : allTransactionList) {
                if (transaction.getStatus().equalsIgnoreCase(status)) {
                    filteredTransactionList.add(transaction);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productQuantity, productPrice;
        ImageView productImage;
        Button transactionStatus;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.product_name);
            productQuantity = itemView.findViewById(R.id.product_quantity);
            transactionStatus = itemView.findViewById(R.id.transaction_status);
            productPrice = itemView.findViewById(R.id.product_price);
            productImage = itemView.findViewById(R.id.product_image);
        }
    }
}