package com.redhun.question_service.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    private String correctAnswer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Long getCategory() {
        return category.getId();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getDifficulty() {
        return difficulty.getId();
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    private int questionType; // Representing MCQ, True/False, etc.

    @ManyToOne
    @JoinColumn(name = "difficulty_id", nullable = false)
    private Difficulty difficulty;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options;

    // Constructors, getters, and setters
    public Question() {}

    public Question(String question, String correctAnswer, int questionType, Difficulty difficulty, Category category, List<Option> options) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.questionType = questionType;
        this.difficulty = difficulty;
        this.category = category;
        this.options = options;
    }

    // Getters and Setters
    // ...
}
