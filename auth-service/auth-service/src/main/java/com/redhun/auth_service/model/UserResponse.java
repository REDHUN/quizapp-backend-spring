package com.redhun.auth_service.model;

import java.util.List;
import java.util.Set;

public class UserResponse {
    private String name;
    private String email;
    private int totalScore;
    private List<String> badges;
    private Set<Role> roles;
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBadges() {
        return badges;
    }

    public void setBadges(List<String> badges) {
        this.badges = badges;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public UserResponse(String name, String email, int totalScore, List<String> badges, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.totalScore = totalScore;
        this.badges = badges;
        this.roles = roles;
    }

    // Getters and Setters
}