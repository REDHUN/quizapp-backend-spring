package com.redhun.auth_service.model;




public class TokenValidationResponse {
    private boolean valid;
    private String username;

    // Private constructor to prevent direct instantiation
    private TokenValidationResponse(Builder builder) {
        this.valid = builder.valid;
        this.username = builder.username;
    }

    // Getters
    public boolean isValid() {
        return valid;
    }

    public String getUsername() {
        return username;
    }

    // Static nested Builder class
    public static class Builder {
        private boolean valid;
        private String username;

        public Builder valid(boolean valid) {
            this.valid = valid;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public TokenValidationResponse build() {
            return new TokenValidationResponse(this);
        }
    }
}
