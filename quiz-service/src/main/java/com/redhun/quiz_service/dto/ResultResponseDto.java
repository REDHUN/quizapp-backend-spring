package com.redhun.quiz_service.dto;

public class ResultResponseDto {
    private Long userId;
    private Long quizId;
    private String quizName;
    private int score;
    private int totalQuestions;

    // Constructor, Getters, and Setters
    public ResultResponseDto(Long userId, Long quizId, String quizName, int score, int totalQuestions) {
        this.userId = userId;
        this.quizId = quizId;
        this.quizName = quizName;
        this.score = score;
        this.totalQuestions = totalQuestions;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
}
