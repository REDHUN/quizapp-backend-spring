package com.redhun.question_service.dto;

import java.util.List;

public class QuestionResponse {
    private Long id;
    private String question;
    private Long correctAnswerId;

    public QuestionResponse(Long id, String question, int questionType, String difficultyName, List<OptionResponse> options, String categoryName, Long correctAnswerId) {
        this.id = id;
        this.question = question;
        this.questionType = questionType;
        this.difficultyName = difficultyName;
        this.options = options;
        this.categoryName = categoryName;
        this.correctAnswerId = correctAnswerId;
    }

    public QuestionResponse() {

    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public Long getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(Long correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<OptionResponse> getOptions() {
        return options;
    }

    public void setOptions(List<OptionResponse> options) {
        this.options = options;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    public void setDifficultyName(String difficultyName) {
        this.difficultyName = difficultyName;
    }

    private int questionType;
    private String categoryName;
    private String difficultyName;
    private List<OptionResponse> options;

    // Getters and setters
    // ...
}


