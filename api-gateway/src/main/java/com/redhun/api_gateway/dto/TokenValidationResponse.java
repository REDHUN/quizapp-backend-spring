package com.redhun.api_gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class TokenValidationResponse {

    @JsonProperty("valid")
    private boolean valid;

    @JsonProperty("username")
    private String username;

    @JsonProperty("role")
    private List<String> roles = new ArrayList<>(); // Initialize roles as an empty list

    // Getters and Setters
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
