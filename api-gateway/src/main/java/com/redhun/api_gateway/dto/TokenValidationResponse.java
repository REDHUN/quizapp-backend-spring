package com.redhun.api_gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenValidationResponse {

    @JsonProperty("valid")
    private boolean valid;

    @JsonProperty("username")
    private String username;

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
}
