//package com.example.mymushroomf.PembeliAdapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.mymushroomf.PembeliActivity.OrderDetailActivity;
//import com.example.mymushroomf.PembeliModel.Order;
//import com.example.mymushroomf.R;
//
//import java.util.List;
//
//public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder> {
//
//    private List<Order> orderList;
//    private Context context;
//
//    public OrderHistoryAdapter(Context context, List<Order> orderList) {
//        this.context = context;
//        this.orderList = orderList;
//    }
//
//    @NonNull
//    @Override
//    public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
//        return new OrderHistoryViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {
//        Order order = orderList.get(position);
//
//        // Bind data to the views in the ViewHolder
//        holder.tvOrderId.setText(order.getOrderId());
//        holder.tvOrderDate.setText(order.getOrderDate());
//        holder.tvShippingDetails.setText(order.getShippingDetails());
//
//        // Set up a click listener on the item
//        holder.itemView.setOnClickListener(v -> {
//            // Create an intent to pass data to OrderDetailActivity
//            Intent intent = new Intent(context, OrderDetailActivity.class);
//            intent.putExtra("orderId", order.getOrderId());
//            intent.putExtra("courier", order.getCourier());
//            intent.putExtra("shippingDetails", order.getShippingDetails());
//            intent.putExtra("paymentMethod", order.getPaymentMethod());
//            intent.putExtra("productCost", order.getProductCost());
//            intent.putExtra("shippingCost", order.getShippingCost());
//            intent.putExtra("totalCost", order.getTotalCost());
//            context.startActivity(intent);
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return orderList.size();
//    }
//
//    public static class OrderHistoryViewHolder extends RecyclerView.ViewHolder {
//        TextView tvOrderId, tvCourier, tvShippingDetails;
//
//        public OrderHistoryViewHolder(View itemView) {
//            super(itemView);
//            tvOrderId = itemView.findViewById(R.id.tvOrderId);
//            tvCourier = itemView.findViewById(R.id.tvCourier);
//            tvShippingDetails = itemView.findViewById(R.id.tvShippingDetails);
//        }
//    }
//}
