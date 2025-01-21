package com.redhun.question_service.models;

import jakarta.persistence.*;

@Entity
@Table(name = "question_type")
public class QuestionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // Example: MCQ, True/False

    // Default constructor (required by Hibernate)
    public QuestionType() {
    }

    // Constructor with parameters
    public QuestionType(String name) {
        this.name = name;
    }

    // Constructor with parameters for both fields
    public QuestionType(Long id, String name) {
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
