package com.redhun.quiz_service.dto;

import java.util.List;

public class QuizResultReport {

    private Long userId;
    private Long quizId;
    private Long resultId; // Add this line
    private String quizName;
    private int score;
    private List<QuestionResult> questionResults;

    public QuizResultReport(Long userId, Long quizId, Long resultId, String quizName, int score, List<QuestionResult> questionResults) {
        this.userId = userId;
        this.quizId = quizId;
        this.resultId = resultId; // Add this line
        this.quizName = quizName;
        this.score = score;
        this.questionResults = questionResults;
    }

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

    public Long getResultId() { // Add this getter
        return resultId;
    }

    public void setResultId(Long resultId) { // Add this setter
        this.resultId = resultId;
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

    public List<QuestionResult> getQuestionResults() {
        return questionResults;
    }

    public void setQuestionResults(List<QuestionResult> questionResults) {
        this.questionResults = questionResults;
    }

    public static class QuestionResult {

        private String question;
        private String userAnswer;
        private String correctAnswer;
        private int scoredMark;

        public QuestionResult(String question, String userAnswer, String correctAnswer, int scoredMark) {
            this.question = question;
            this.userAnswer = userAnswer;
            this.correctAnswer = correctAnswer;
            this.scoredMark = scoredMark;
        }

        // Getters and Setters

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getUserAnswer() {
            return userAnswer;
        }

        public void setUserAnswer(String userAnswer) {
            this.userAnswer = userAnswer;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public void setCorrectAnswer(String correctAnswer) {
            this.correctAnswer = correctAnswer;
        }

        public int getScoredMark() {
            return scoredMark;
        }

        public void setScoredMark(int scoredMark) {
            this.scoredMark = scoredMark;
        }
    }
}
