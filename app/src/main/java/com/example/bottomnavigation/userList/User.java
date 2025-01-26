package com.example.bottomnavigation.userList;

public class User {
    private String avatar;
    private String username;
    private String email;
    private String role;

    public User(String avatar, String username, String email, String role) {
        this.avatar = avatar;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    // Getter methods
    public String getAvatar() {
        return avatar;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
