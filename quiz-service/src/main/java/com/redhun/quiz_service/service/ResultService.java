package com.redhun.quiz_service.service;

import com.redhun.quiz_service.dto.*;
import com.redhun.quiz_service.feign.QuizInterface;
import com.redhun.quiz_service.model.Quiz;
import com.redhun.quiz_service.model.Result;
import com.redhun.quiz_service.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ResultService {
    @Autowired
    QuizInterface quizInterface;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    QuizService quizService;

    @Autowired
    public ResultService(QuizInterface quizInterface, ResultRepository resultRepository) {
        this.quizInterface = quizInterface;
        this.resultRepository = resultRepository;
    }

    public ResultResponseDto calculateResult(UserResponseDto userResponses) {
        // Step 1: Extract question IDs from user responses
        List<Long> questionIds = extractQuestionIds(userResponses);

        // Step 2: Fetch questions from Question Service
        List<QuestionResponseDto> questions = fetchQuestionsFromService(questionIds);

        // Step 3: Create a map of correct answers for validation
        Map<Long, Long> correctAnswerMap = createCorrectAnswerMap(questions);

        // Step 4: Calculate the user's score
        int score = calculateScore(userResponses, correctAnswerMap);

        // Step 5: Fetch quiz details
        Quiz quiz = fetchQuizDetails(userResponses.getQuizId());

        // Step 6: Save the result in the database
        saveResult(userResponses, questionIds, score);

        // Step 7: Return the response DTO with the calculated score, total number of questions, and quiz name
        return new ResultResponseDto(userResponses.getUserId(), userResponses.getQuizId(), quiz.getTitle(), score, questionIds.size());
    }

    private List<Long> extractQuestionIds(UserResponseDto userResponses) {
        return userResponses.getUserAnswers()
                .stream()
                .map(UserAnswerResponse::getQuestionId)
                .toList();
    }

    private List<QuestionResponseDto> fetchQuestionsFromService(List<Long> questionIds) {
        List<QuestionResponseDto> questions = quizInterface.getQuestionsFromId(questionIds).getBody();
        if (questions == null) {
            throw new RuntimeException("Failed to fetch questions from the Question Service.");
        }
        return questions;
    }

    private Quiz fetchQuizDetails(Long quizId) {
        Quiz quiz = quizService.getQuiz(quizId).getBody();
        if (quiz == null) {
            throw new RuntimeException("Failed to fetch quiz details from the Quiz Service.");
        }
        return quiz;
    }

    private Map<Long, Long> createCorrectAnswerMap(List<QuestionResponseDto> questions) {
        return questions.stream()
                .collect(Collectors.toMap(
                        QuestionResponseDto::getId,
                        QuestionResponseDto::getCorrectAnswerId
                ));
    }

    private int calculateScore(UserResponseDto userResponses, Map<Long, Long> correctAnswerMap) {
        return (int) userResponses.getUserAnswers().stream()
                .filter(answer -> {
                    Long correctAnswerId = correctAnswerMap.get(answer.getQuestionId());
                    return correctAnswerId != null && correctAnswerId.equals(answer.getUserAnswer());
                })
                .count();
    }

    private void saveResult(UserResponseDto userResponses, List<Long> questionIds, int score) {
        Result result = new Result(
                userResponses.getUserId(),
                userResponses.getQuizId(),
                score,
                questionIds,
                userResponses.getUserAnswers().stream()
                        .map(UserAnswerResponse::getUserAnswer)
                        .toList()
        );
        resultRepository.save(result);
    }

    public List<QuizResultReport> getAttendQuizResultReport(Long userId) {
        List<Result> results;
        if (userId != null) {
            results = resultRepository.findByUserId(userId);
        } else {
            results = resultRepository.findAll();
        }

        return results.stream()
                .map(this::mapToQuizResultReport)
                .collect(Collectors.toList());
    }

    private QuizResultReport mapToQuizResultReport(Result result) {
        // Fetch quiz details
        Quiz quiz = quizService.getQuiz(result.getQuizId()).getBody();
        String quizName = (quiz != null) ? quiz.getTitle() : "N/A";

        // Fetch all questions at once
        List<QuestionResponseDto> questions = quizInterface.getQuestionsFromId(result.getQuestionIds()).getBody();
        Map<Long, QuestionResponseDto> questionMap = questions.stream()
                .collect(Collectors.toMap(QuestionResponseDto::getId, Function.identity()));

        List<QuizResultReport.QuestionResult> questionResults = result.getQuestionIds().stream()
                .map(questionId -> {
                    QuestionResponseDto question = questionMap.get(questionId);
                    String userAnswer = (question != null) ? getUserAnswer(result.getUserAnswers(), result.getQuestionIds(), question.getOptions(), questionId) : "N/A";
                    String correctAnswer = (question != null) ? getCorrectAnswer(question) : "N/A";

                    int scoredMark = correctAnswer.equals(userAnswer) ? 1 : 0;
                    return new QuizResultReport.QuestionResult(
                            (question != null) ? question.getQuestion() : "N/A",
                            userAnswer,
                            correctAnswer,
                            scoredMark
                    );
                })
                .collect(Collectors.toList());

        return new QuizResultReport(
                result.getUserId(),
                result.getQuizId(),
                result.getId(),
                quizName,
                result.getScore(),
                questionResults
        );
    }


    private String getCorrectAnswer(QuestionResponseDto question) {
        // Assuming the correct answer can be identified by some property in the options
        if (question != null && question.getOptions() != null) {
            return question.getOptions().stream()
                    .filter(option -> option.getId().equals(question.getCorrectAnswerId()))
                    .findFirst()
                    .map(OptionResponseDto::getText)
                    .orElse("N/A");
        }
        return "N/A";
    }

    private String getUserAnswer(List<Long> userAnswers, List<Long> questionIds, List<OptionResponseDto> options, Long questionId) {
        // Retrieve user's answer for a specific questionId
        // Assuming userAnswers list contains the user's answers in order of the question IDs
        int index = questionIds.indexOf(questionId);
        if (index != -1) {
            Long userAnswerId = userAnswers.get(index);
            return options.stream()
                    .filter(option -> option.getId().equals(userAnswerId))
                    .findFirst()
                    .map(OptionResponseDto::getText)
                    .orElse("N/A");
        }
        return "N/A";
    }
}
