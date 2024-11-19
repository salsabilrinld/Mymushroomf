package com.example.mymushroomf.PembeliAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mymushroomf.PembeliModel.Address;
import com.example.mymushroomf.R;
import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private Context context;
    private List<Address> addressList;
    private int selectedPosition = -1; // Menyimpan posisi item yang dipilih

    public AddressAdapter(Context context, List<Address> addressList) {
        this.context = context;
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public AddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_address, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.ViewHolder holder, int position) {
        Address address = addressList.get(position);
        holder.nameTextView.setText(address.getName());
        holder.phoneTextView.setText(address.getPhoneNumber());
        holder.addressTextView.setText(address.getAddress());

        // Mengubah warna item yang dipilih
        if (position == selectedPosition) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#80D58D43")); // Warna orange transparan
        } else {
            holder.cardView.setCardBackgroundColor(Color.WHITE); // Warna default
        }

        // Menangani klik item
        holder.cardView.setOnClickListener(v -> {
            int clickedPosition = holder.getAdapterPosition(); // Get the current position
            if (clickedPosition != RecyclerView.NO_POSITION) {
                selectedPosition = clickedPosition;  // Update selected position
                notifyDataSetChanged();  // Update RecyclerView to reflect changes
                Toast.makeText(context, "Alamat yang dipilih: " + address.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView, phoneTextView, addressTextView;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.addressName);
            phoneTextView = itemView.findViewById(R.id.addressNumber);
            addressTextView = itemView.findViewById(R.id.addressLine);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
