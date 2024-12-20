package com.example.mymushroomf.PembeliAdapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushroomf.PembeliModel.Notifications;
import com.example.mymushroomf.R;

import java.util.ArrayList;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {

    private ArrayList<Notifications> notifications;

    public NotificationsAdapter(ArrayList<Notifications> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notifications_list, parent, false);
        return new NotificationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int position) {
        Notifications notificationsItem = notifications.get(position);

        holder.tvTitle.setText(notificationsItem.getTitle());
        holder.tvMessage.setText(notificationsItem.getMessage());
        holder.tvTimestamp.setText(notificationsItem.getTimestamp());

        if (notificationsItem.isRead()) {
            holder.itemView.setBackgroundColor(Color.WHITE); // Mark as read
        } else {
            holder.itemView.setBackgroundColor(Color.LTGRAY); // Unread
        }

        // Handle click to mark as read
        holder.itemView.setOnClickListener(view -> {
            notificationsItem.setRead(true); // Mark the notification as read
            notifyItemChanged(position); // Refresh the item
        });
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public static class NotificationsViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvMessage, tvTimestamp;

        public NotificationsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.notifications_title);
            tvMessage = itemView.findViewById(R.id.notifications_message);
            tvTimestamp = itemView.findViewById(R.id.notifications_timestamp);
        }
    }
}
