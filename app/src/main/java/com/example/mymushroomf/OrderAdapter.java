package com.example.mymushroomf;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orderList;
    private OnOrderClickListener listener;

    public OrderAdapter(List<Order> orderList, OnOrderClickListener listener) {
        this.orderList = orderList;
        this.listener = listener;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.tvStatusOrder.setText(order.getOrderStatus());
        holder.tvProductInfo.setText(order.getProductInfo());
        holder.tvCourierDetail.setText(order.getOrderCourier());
        holder.tvResiDetail.setText(order.getOrderResi());
        holder.tvAddressDetail.setText(order.getOrderAddress());
        holder.tvPaymentMethod.setText(order.getOrderMethod());
        holder.tvProductCost.setText(order.getOrderCostProduct());
        holder.tvDeliveryCost.setText(order.getOrderCostDelivery());
        holder.tvTotalCost.setText(order.getOrderTotal());

//        // Optional: handle click events to open OrderDetailActivity with details of a selected order
//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, OrderDetailActivity.class);
//            intent.putExtra("orderDetail", order);  // Send orderDetail object to the activity
//            context.startActivity(intent);
//        });
        holder.itemView.setOnClickListener(v -> listener.onOrderClick(order));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView tvStatusOrder, tvProductInfo, tvCourierDetail, tvResiDetail, tvAddressDetail,
                tvPaymentMethod, tvProductCost, tvDeliveryCost, tvTotalCost;

        public OrderViewHolder(@NonNull View itemView) {
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

    public interface OnOrderClickListener {
        void onOrderClick(Order order);
    }
}
