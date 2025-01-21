package com.redhun.question_service.models;

import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDateTime;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private String correctAnswer;

    private String createdBy;

    private LocalDateTime createdTime;

    @ManyToOne
    @JoinColumn(name = "difficulty_id", nullable = false)
    private Difficulty difficulty;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "question_type_id", nullable = false)
    private QuestionType questionType;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options;

    // Constructors, getters, and setters
    public Question() {}

    public Question(String question, String correctAnswer, String createdBy, LocalDateTime createdTime, Difficulty difficulty, Category category, List<Option> options, QuestionType questionType) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.difficulty = difficulty;
        this.category = category;
        this.options = options;
        this.questionType = questionType;
    }

    // Getters and setters
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

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
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

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }
}
