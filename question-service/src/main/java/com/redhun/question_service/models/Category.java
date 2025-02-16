package com.redhun.question_service.models;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // Example: Science, History, Math

    @Column(nullable = false)
    private String isDeleted;

    @Column(nullable = false)
    private String isActive;

    // Default constructor (required by Hibernate)
    public Category() {
        this.isDeleted = "N"; // Default value
        this.isActive = "N"; // Default value
    }

    // Constructor with parameters
    public Category(String name) {
        this.name = name;
        this.isDeleted = "N"; // Default value
        this.isActive = "N"; // Default value
    }

    // Constructor with parameters for all fields
    public Category(Long id, String name, String isDeleted, String isActive) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
        this.isActive = isActive;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
