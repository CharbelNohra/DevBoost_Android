package com.example.bottomnavigation.userList;

public class User {
    private String full_name;
    private String email;
    private int role_id;
    private int user_id;

    public User(String full_name, String email, int role_id, int user_id) {
        this.full_name = full_name;
        this.email = email;
        this.role_id = role_id;
        this.user_id = user_id;
    }

    // Getter methods
    public String getFull_name() {
        return full_name;
    }

    public String getEmail() {
        return email;
    }

    public int getRole_id() {
        return role_id;
    }

    public int getUser_id() {
        return user_id;
    }

    // Optional: toString for easier logging
    @Override
    public String toString() {
        return "User{" +
                "full_name='" + full_name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role_id + '\'' +
                '}';
    }
}
