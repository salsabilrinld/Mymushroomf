package com.example.mymushroomf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NotificationsAdapter notificationsAdapter;
    private List<Notifications> notificationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notifications);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


                recyclerView = findViewById(R.id.recyler_viewnotifications);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                // Inisialisasi list notifikasi
                notificationsList = new ArrayList<>();
                loadNotifications();

                // Inisialisasi adapter dan set ke RecyclerView
                notificationsAdapter = new NotificationsAdapter(notificationsList);
                recyclerView.setAdapter(notificationsAdapter);

            ImageButton backButton = findViewById(R.id.back_notifications);

            backButton.setOnClickListener(view -> finish());
            }

            private void loadNotifications() {
                // Contoh data notifikasi
                notificationsList.add(new Notifications("Pesanan", "Pesanan Jamur Tiram telah diterima.", "2 mins ago"));
                notificationsList.add(new Notifications("Pesanan", "Pesanan Jamur Kancing sudah dikirimkan", "1 hour ago"));
                notificationsList.add(new Notifications("Ulasan", "Ulasan telah dihapus", "3 hours ago"));
                // Tambahkan lebih banyak data sesuai kebutuhan
            }

        }
