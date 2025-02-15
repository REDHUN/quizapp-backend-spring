package com.redhun.question_service.dto;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionResponse {
    private Long id;
    private String question;
    private Long correctAnswerId;
    private String isDeleted; // Add this field

    private String categoryName;
    private String dificaltyName;
    private Long categoryId;
    private Long difficultyId;
    private Long questionTypeId;
    private String questionTypeName;
    private List<OptionResponse> options;
    private String createdBy;
    private LocalDateTime createdTime;

    public QuestionResponse(Long id, String question, Long difficultyId, List<OptionResponse> options, Long categoryId, Long correctAnswerId, String createdBy, LocalDateTime createdTime, String categoryName, String dificaltyName, Long questionTypeId, String questionTypeName, String isDeleted) {
        this.id = id;
        this.question = question;
        this.difficultyId = difficultyId;
        this.options = options;
        this.categoryId = categoryId;
        this.correctAnswerId = correctAnswerId;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.categoryName = categoryName;
        this.dificaltyName = dificaltyName;
        this.questionTypeId = questionTypeId;
        this.questionTypeName = questionTypeName;
        this.isDeleted = isDeleted;
    }

    public QuestionResponse() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDificaltyName() {
        return dificaltyName;
    }

    public void setDificaltyName(String dificaltyName) {
        this.dificaltyName = dificaltyName;
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

    public String getQuestionTypeName() {
        return questionTypeName;
    }

    public void setQuestionTypeName(String questionTypeName) {
        this.questionTypeName = questionTypeName;
    }

    public List<OptionResponse> getOptions() {
        return options;
    }

    public void setOptions(List<OptionResponse> options) {
        this.options = options;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
