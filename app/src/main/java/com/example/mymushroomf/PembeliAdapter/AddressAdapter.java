package com.example.mymushroomf.PembeliAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.PembeliModel.Address;
import com.example.mymushroomf.R;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {
    private List<Address> addressList;
    private int selectedPosition = -1;
    private Context context;

    public AddressAdapter(Context context, List<Address> addresses) {
        this.addressList = addresses;
        this.context = context;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);
        return new AddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        Address address = addressList.get(position);

        holder.name.setText(address.getName());
        holder.type.setText(address.getType());
        holder.phone.setText(address.getPhoneNumber());
        holder.addressLine.setText(address.getAddress());

        // Check if this is the selected item and set the background color accordingly
        if (selectedPosition == position) {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.card_selected));
        } else {
            holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.card_default));
        }

        holder.itemView.setOnClickListener(v -> {
            int previousPosition = selectedPosition;
            selectedPosition = holder.getAdapterPosition();

            // Notify item changes to update the background color
            notifyItemChanged(previousPosition);
            notifyItemChanged(selectedPosition);
        });
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public static class AddressViewHolder extends RecyclerView.ViewHolder {
        TextView name, phone, addressLine, type;
        CardView cardView;

        public AddressViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.recycler_viewaddress); // Ensure this ID is correct
            name = itemView.findViewById(R.id.addressName);
            phone = itemView.findViewById(R.id.addressNumber);
            addressLine = itemView.findViewById(R.id.addressLine);
            type = itemView.findViewById(R.id.addressType);
        }
    }
}
