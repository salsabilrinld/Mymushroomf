package com.example.mymushroomf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private List<Address> addressList;

    public AddressAdapter(List<Address> addressList) {
        this.addressList = addressList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView phoneNumberTextView;
        TextView addressTextView;
        TextView typeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.addressName);
            phoneNumberTextView = itemView.findViewById(R.id.addressNumber);
            addressTextView = itemView.findViewById(R.id.address);
            typeTextView = itemView.findViewById(R.id.addressType);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Address address = addressList.get(position);
        holder.nameTextView.setText(address.getName());
        holder.phoneNumberTextView.setText(address.getPhoneNumber());
        holder.addressTextView.setText(address.getAddress());
        holder.typeTextView.setText(address.getType());
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }
}

