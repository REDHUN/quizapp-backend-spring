package com.redhun.quiz_service.dto;



public class  UserAnswerResponse {
    private Long questionId;
    private Long userAnswer;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(Long userAnswer) {
        this.userAnswer = userAnswer;
    }
}