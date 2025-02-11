package com.example.bottomnavigation.botNavBar;

public class NotificationRequest {
    private String title;
    private String description;

    // Constructor
    public NotificationRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String message) {
        this.description = message;
    }
}