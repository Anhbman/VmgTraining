package com.vmg.buoi10_thuchanhjwt.payload.response;

import com.vmg.buoi10_thuchanhjwt.model.ERole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

public class JwtResponse {

    @NotNull(message = "token cannot null")
    private String token;

    @NotNull(message = "type cannot null")
    private String type;

    @NotNull(message = "id cannot null")
    private Long id;

    @NotNull(message = "username cannot null")
    private String username;

    @NotNull(message = "email cannot null")
    @Email
    private String email;

    @NotNull(message = "role cannot null")
    private List<String> role;

    public JwtResponse(String jwt, String username, String email, List<String> roles) {
        this.token = jwt;
        this.username = username;
        this.email = email;
        this.role = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
