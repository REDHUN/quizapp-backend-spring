package com.redhun.question_service.dto.edit_question_dto;

public class EditOptionDto {
    private Long id;
    private String text;

    // Default constructor
    public EditOptionDto() {}

    // Constructor with parameters
    public EditOptionDto(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
