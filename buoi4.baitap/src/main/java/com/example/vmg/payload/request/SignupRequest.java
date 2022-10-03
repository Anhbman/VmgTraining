package com.example.vmg.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SignupRequest {

    @NotNull(message = "username cannot null")
    private String username;

    @NotNull(message = "fullname cannot null")
    private String fullname;

    @NotNull(message = "password cannot null")
    private String password;

//    @NotNull(message = "Role cannot null")
    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
