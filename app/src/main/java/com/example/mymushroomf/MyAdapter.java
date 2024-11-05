package com.example.mymushroomf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;




public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<String> mData;
    private List<String> fullList;
    private List<String> filteredList;
    private List<Product> productList;
    private Context context;

    public MyAdapter(List<String> data) {

        mData = data;
        this.fullList = new ArrayList<>(data);
        this.filteredList = new ArrayList<>(data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textItem.setText(mData.get(position));
        holder.textItem.setText(filteredList.get(position));
//        holder.addButton.setOnClickListener(view -> {
//
//        });
    }

    @Override
    public int getItemCount() {

//        return mData.size();
        return filteredList.size();
    }

    public void filter(String query) {
        if (query.isEmpty()) {
            filteredList = new ArrayList<>(fullList);
        } else {
            List<String> filtered = new ArrayList<>();
            for (String item : fullList) {
                if (item.toLowerCase().contains(query.toLowerCase())) {
                    filtered.add(item);
                }
            }
            filteredList = filtered;
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textItem;
        public Button addButton;

        public ViewHolder(View itemView) {
            super(itemView);
            textItem = itemView.findViewById(R.id.product_item);
            addButton = itemView.findViewById(R.id.button_addproduct);
        }
    }
}


