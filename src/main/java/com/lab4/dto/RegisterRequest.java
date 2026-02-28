package com.lab4.dto;

public class RegisterRequest {
    private String username;
    private String password;
    private String ownerName;

    public RegisterRequest(String username, String password, String ownerName) {
        this.username = username;
        this.password = password;
        this.ownerName = ownerName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
