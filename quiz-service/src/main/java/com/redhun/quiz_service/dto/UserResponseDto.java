package com.redhun.quiz_service.dto;

import java.util.List;

public class UserResponseDto {
    private Long userId;
    private Long quizId;
    private List<UserAnswerResponse> userAnswers;

    // Getters and Setters
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

    public List<UserAnswerResponse> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswerResponse> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
