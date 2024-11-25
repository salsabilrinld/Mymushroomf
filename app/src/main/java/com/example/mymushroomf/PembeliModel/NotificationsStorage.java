package com.example.mymushroomf.PembeliModel;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NotificationsStorage {
    private static final String PREFS_NAME = "notifications_prefs";
    private static final String KEY_NOTIFICATIONS = "notifications_list";

    public static void saveNotifications(Context context, Notifications notificationsList) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();

        String json = prefs.getString(KEY_NOTIFICATIONS, "[]"); // Default ke array kosong
        Type type = new TypeToken<ArrayList<Notifications>>() {}.getType();
        ArrayList<Notifications> notifications = gson.fromJson(json, type);

        // Tambahkan notifikasi baru
        notifications.add(notificationsList);

        // Simpan kembali sebagai JSON Array
        String updatedJson = gson.toJson(notifications);
        editor.putString("notifications_list", updatedJson);
        editor.apply();
    }

    public static ArrayList<Notifications> getNotifications(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json = prefs.getString(KEY_NOTIFICATIONS, null);
        Type type = new TypeToken<ArrayList<Notifications>>() {}.getType();

        return gson.fromJson(json, type);
    }
}

