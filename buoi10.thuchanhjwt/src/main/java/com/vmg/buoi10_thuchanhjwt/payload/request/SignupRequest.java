package com.vmg.buoi10_thuchanhjwt.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SignupRequest {

    @NotNull(message = "email cannot null")
    @Email
    private String email;

    @NotNull(message = "username cannot null")
    private String username;

    @NotNull(message = "password cannot null")
    private String password;

    @NotNull(message = "Role cannot null")
    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
