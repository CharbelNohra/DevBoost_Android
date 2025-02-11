package com.example.bottomnavigation.Profile;

public class UpdatePasswordRequest {
    private String newPassword;

    public UpdatePasswordRequest(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}