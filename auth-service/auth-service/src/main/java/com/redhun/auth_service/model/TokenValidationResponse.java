package com.redhun.auth_service.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class TokenValidationResponse {
    private boolean valid;
    private String username;
    private List<String> role;

    // Default constructor
    public TokenValidationResponse() {
    }

    // Parameterized constructor
    public TokenValidationResponse(boolean valid, String username, List<String> role) {
        this.valid = valid;
        this.username = username;
        this.role = role;
    }

    // Getter and Setter methods
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

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    // Builder pattern for easy object creation
    public static class Builder {
        private boolean valid;
        private String username;
        private List<String> role;

        public Builder() {
        }

        public Builder valid(boolean valid) {
            this.valid = valid;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder role(List<String> role) {
            this.role = role;
            return this;
        }

        public TokenValidationResponse build() {
            return new TokenValidationResponse(valid, username, role);
        }
    }
}
