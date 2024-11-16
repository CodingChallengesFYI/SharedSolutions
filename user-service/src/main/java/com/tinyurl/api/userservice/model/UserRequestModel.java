package com.tinyurl.api.userservice.model;

public class UserRequestModel {

    private String userId;
    private String email;
    private String password;
    private String username;

    public UserRequestModel(String userId, String username, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId='" + userId + '\'' +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
