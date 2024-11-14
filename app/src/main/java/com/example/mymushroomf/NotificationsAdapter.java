package com.example.mymushroomf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {
    private List<Notifications> notificationsList;

    public NotificationsAdapter(List<Notifications> notificationsList) {
        this.notificationsList = notificationsList;
    }

    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notifications_list, parent, false);
        return new NotificationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int position) {
        Notifications notifications = notificationsList.get(position);
        holder.titleTextView.setText(notifications.getTitle());
        holder.messageTextView.setText(notifications.getMessage());
        holder.timestampTextView.setText(notifications.getTimestamp());
    }

    @Override
    public int getItemCount() {
        return notificationsList.size();
    }

    public static class NotificationsViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView messageTextView;
        TextView timestampTextView;

        public NotificationsViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.notifications_title);
            messageTextView = itemView.findViewById(R.id.notifications_message);
            timestampTextView = itemView.findViewById(R.id.notifications_timestamp);
        }
    }
}

