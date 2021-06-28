package com.example.patientrecord.resource;


import javax.validation.constraints.NotEmpty;

public class RegistrationRequest {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String username, String password) {
        this.username = username;
        this.password = password;
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
}
