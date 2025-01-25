package com.redhun.quiz_service.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId; // User's ID
    private Long quizId; // Quiz's ID
    private int score;   // Total score

    @ElementCollection
    private List<Long> questionIds; // List of question IDs

    @ElementCollection
    private List<Long> userAnswers; // List of user's answers

    // Constructors, Getters, and Setters
    public Result() {}

    public Result(Long userId, Long quizId, int score, List<Long> questionIds, List<Long> userAnswers) {
        this.userId = userId;
        this.quizId = quizId;
        this.score = score;
        this.questionIds = questionIds;
        this.userAnswers = userAnswers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }

    public List<Long> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<Long> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
