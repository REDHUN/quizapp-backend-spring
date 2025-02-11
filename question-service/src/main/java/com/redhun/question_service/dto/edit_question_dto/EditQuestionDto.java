package com.redhun.question_service.dto.edit_question_dto;

import java.util.List;

public class EditQuestionDto {

    private String question;
    private Long correctAnswerId;
    private Long categoryId;
    private Long difficultyId;
    private Long questionTypeId;
    private List<EditOptionDto> options;
    private String createdBy;

    // Default constructor
    public EditQuestionDto() {}

    // Constructor with parameters
    public EditQuestionDto(String question, Long correctAnswerId, Long categoryId, Long difficultyId, Long questionTypeId, List<EditOptionDto> options, String createdBy) {
        this.question = question;
        this.correctAnswerId = correctAnswerId;
        this.categoryId = categoryId;
        this.difficultyId = difficultyId;
        this.questionTypeId = questionTypeId;
        this.options = options;
        this.createdBy = createdBy;
    }

    // Getters and setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(Long correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(Long difficultyId) {
        this.difficultyId = difficultyId;
    }

    public Long getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Long questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public List<EditOptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<EditOptionDto> options) {
        this.options = options;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
