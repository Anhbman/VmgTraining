package com.example.vmg.payload.request;

import javax.validation.constraints.NotNull;

public class LoginRequest {

    @NotNull(message = "username cannot null")
    private String username;

    @NotNull(message = "password cannot null")
    private String password;

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
