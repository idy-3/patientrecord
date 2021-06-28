package com.example.patientrecord.resource;


public class UserResponse {
    private String token;

    public UserResponse() {
    }

    public UserResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
