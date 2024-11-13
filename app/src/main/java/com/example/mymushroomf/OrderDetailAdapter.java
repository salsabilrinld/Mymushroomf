package com.example.mymushroomf;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {

    private final Context context;
    private final List<Order> orderList;

    public OrderDetailAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_orderdetail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orderList.get(position);

        // Set data to TextViews inside each LinearLayout within the ViewHolder
        holder.tvStatusOrder.setText(order.getOrderStatus());
        holder.tvProductInfo.setText(order.getProductInfo());
        holder.tvCourierDetail.setText(order.getOrderCourier());
        holder.tvResiDetail.setText(order.getOrderResi());
        holder.tvAddressDetail.setText(order.getOrderAddress());
        holder.tvPaymentMethod.setText(order.getOrderMethod());
        holder.tvProductCost.setText(order.getOrderCostProduct());
        holder.tvDeliveryCost.setText(order.getOrderCostDelivery());
        holder.tvTotalCost.setText(order.getOrderTotal());

        // Optional: handle click events to open OrderDetailActivity with details of a selected order
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, OrderDetailActivity.class);
            intent.putExtra("orderDetail", order);  // Send orderDetail object to the activity
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Declare TextView variables for each data element in the order detail
        TextView tvStatusOrder, tvProductInfo, tvCourierDetail, tvResiDetail, tvAddressDetail,
                tvPaymentMethod, tvProductCost, tvDeliveryCost, tvTotalCost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize TextViews by finding them within their respective LinearLayouts
            tvStatusOrder = itemView.findViewById(R.id.status_order);
            tvProductInfo = itemView.findViewById(R.id.info_product);
            tvCourierDetail = itemView.findViewById(R.id.tvkurir_detail);
            tvResiDetail = itemView.findViewById(R.id.tvresi_detail);
            tvAddressDetail = itemView.findViewById(R.id.tvalamat_detail);
            tvPaymentMethod = itemView.findViewById(R.id.tvmetode_pembayaran);
            tvProductCost = itemView.findViewById(R.id.tvbiaya_produk);
            tvDeliveryCost = itemView.findViewById(R.id.tvbiaya_kirim);
            tvTotalCost = itemView.findViewById(R.id.tvtotal_biaya);
        }
    }
}
