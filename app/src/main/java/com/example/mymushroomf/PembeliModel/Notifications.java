package com.example.mymushroomf.PembeliModel;

public class Notifications {
    private String title;
    private String message;
    private String timestamp;

    // Constructor
    public Notifications(String title, String message, String timestamp) {
        this.title = title;
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

