package com.redhun.question_service.dto;

import java.util.List;

public class QuestionRequest {
    private String question;

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public QuestionRequest(String question, List<OptionRequest> options, Long difficultyId, int questionType, String correctAnswer, Long categoryId) {
        this.question = question;
        this.options = options;
        this.difficultyId = difficultyId;
        this.questionType = questionType;
        this.correctAnswer = correctAnswer;
        this.categoryId = categoryId;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<OptionRequest> getOptions() {
        return options;
    }

    public void setOptions(List<OptionRequest> options) {
        this.options = options;
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

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    private String correctAnswer;
    private int questionType;
    private Long difficultyId;
    private Long categoryId;
    private List<OptionRequest> options;

    // Getters and setters
    // ...
}

