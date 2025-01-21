package com.redhun.question_service.dto;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionResponse {
    private Long id;
    private String question;
    private Long correctAnswerId;



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

    private String categoryName;
    private String dificaltyName;

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

    private  Long questionTypeId;

    public String getQuestionTypeName() {
        return questionTypeName;
    }

    public void setQuestionTypeName(String questionTypeName) {
        this.questionTypeName = questionTypeName;
    }

    public Long getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Long questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    private String questionTypeName;
    private Long categoryId;
    private Long difficultyId;
    private List<OptionResponse> options;
    private String createdBy;
    private LocalDateTime createdTime;

    public QuestionResponse(Long id, String question,  Long difficultyId, List<OptionResponse> options, Long categoryId, Long correctAnswerId, String createdBy, LocalDateTime createdTime,String categoryName,String dificaltyName,Long questionTypeId,String questionTypeName) {
        this.id = id;
        this.question = question;
this.questionTypeId=questionTypeId;
this.questionTypeName=questionTypeName;
this.categoryName=categoryName;
this.dificaltyName=dificaltyName;
        this.options = options;
        this.categoryId = categoryId;
        this.difficultyId = difficultyId;
        this.correctAnswerId = correctAnswerId;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
    }

    public QuestionResponse() {
    }

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
