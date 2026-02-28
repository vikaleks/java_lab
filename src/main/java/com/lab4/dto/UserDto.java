package com.lab4.dto;

public class UserDto {
    private String username;
    private String password;
    private Long ownerId;

    public UserDto() {
    }

    public UserDto(String username, String password, Long ownerId) {
        this.username = username;
        this.password = password;
        this.ownerId = ownerId;
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}