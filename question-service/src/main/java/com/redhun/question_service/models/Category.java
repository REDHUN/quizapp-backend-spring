package com.redhun.question_service.models;

import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // Example: Science, History, Math

    // Default constructor (required by Hibernate)
    public Category() {
    }

    // Constructor with parameters
    public Category(String name) {
        this.name = name;
    }

    // Constructor with parameters for both fields
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
