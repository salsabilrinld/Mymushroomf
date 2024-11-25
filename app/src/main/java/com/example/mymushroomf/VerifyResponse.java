package com.example.mymushroomf;

public class VerifyResponse {
    private String status;
    private String message;

    // Getters
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    // Setters (opsional)
    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}