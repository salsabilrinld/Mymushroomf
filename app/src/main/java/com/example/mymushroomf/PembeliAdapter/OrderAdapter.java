package com.example.mymushroomf.PembeliAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.PembeliActivity.OrderDetailActivity;
import com.example.mymushroomf.PembeliModel.Order;
import com.example.mymushroomf.PembeliModel.OrderDetail;
import com.example.mymushroomf.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orderList;
    private Context context;
    private List<Order> filteredOrderList;


    public OrderAdapter(Context context, List<Order> orderList) {
        this.orderList = orderList;
        this.context = context;
        this.filteredOrderList = new ArrayList<>(orderList);
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transaction, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);

        holder.productImage.setImageResource(order.getOrderDetails().get(0).getProduct().getImageResId());
        holder.productName.setText(order.getOrderDetails().get(0).getProduct().getName());
        holder.productQuantity.setText(String.valueOf(order.getOrderDetails().get(0).getQuantity()) + " kg");
        holder.orderStatus.setText(order.getOrderDetails().get(0).getStatus());
        holder.productPrice.setText(holder.formatCurrency(order.getOrderDetails().get(0).getTotalPayment()));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, OrderDetailActivity.class);
            intent.putExtra("order", order.getOrderId()); //
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return filteredOrderList.size();
    }

    public void filter(String status) {
        filteredOrderList.clear();

        if (status.equalsIgnoreCase("Semua")) {
            filteredOrderList.addAll(orderList); // Tampilkan semua data
        } else {
            for (Order order : orderList) {
                for (OrderDetail detail : order.getOrderDetails()) {
                    if (detail.getStatus().equalsIgnoreCase(status)) {
                        filteredOrderList.add(order);
                        break; // Tambahkan sekali untuk setiap Order
                    }
                }
            }
        }

        notifyDataSetChanged(); // Refresh RecyclerView
    }



    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productQuantity, productPrice;
        ImageView productImage;
        Button orderStatus;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.product_name);
            productQuantity = itemView.findViewById(R.id.product_quantity);
            orderStatus = itemView.findViewById(R.id.transaction_status);
            productPrice = itemView.findViewById(R.id.product_price);
            productImage = itemView.findViewById(R.id.product_image);
        }

        private String formatCurrency(int amount) {
            NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
            return format.format(amount).replace("Rp", "Rp. ").replace(",00", "");
        }
    }
}
