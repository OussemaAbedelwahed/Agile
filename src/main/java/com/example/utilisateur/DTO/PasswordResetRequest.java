package com.example.utilisateur.DTO;

import com.example.utilisateur.Entity.PasswordResetToken;
import com.example.utilisateur.Entity.Utilisateur;
import lombok.Getter;
import lombok.Setter;


public class PasswordResetRequest {
    private String email;
    private String token;
    private String newPassword;

    // getters and setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}