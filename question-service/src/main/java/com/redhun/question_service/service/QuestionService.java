package com.redhun.question_service.service;

import com.redhun.question_service.dto.OptionResponse;
import com.redhun.question_service.dto.QuestionRequest;
import com.redhun.question_service.dto.QuestionResponse;
import com.redhun.question_service.models.*;
import com.redhun.question_service.repository.CategoryRepository;
import com.redhun.question_service.repository.DifficultyRepository;
import com.redhun.question_service.repository.QuestionRepository;
import com.redhun.question_service.repository.QuestionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DifficultyRepository difficultyRepository;
    @Autowired
    private QuestionTypeRepository questionTypeRepository;

    @Transactional
    public QuestionResponse createQuestion(QuestionRequest questionRequest) {
        Category category = categoryRepository.findById(questionRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Difficulty difficulty = difficultyRepository.findById(questionRequest.getDifficultyId())
                .orElseThrow(() -> new RuntimeException("Difficulty not found"));
        QuestionType questionType = questionTypeRepository.findById(questionRequest.getQuestionType())
                .orElseThrow(() -> new RuntimeException("Difficulty not found"));

        Question question = new Question();
        question.setQuestion(questionRequest.getQuestion());
        question.setCorrectAnswer(questionRequest.getCorrectAnswer());
        question.setQuestionType(questionType);
        question.setCategory(category);
        question.setDifficulty(difficulty);
        question.setCreatedBy(questionRequest.getCreatedBy());  // Replace with actual user
        question.setCreatedTime(LocalDateTime.now());

        List<Option> options = questionRequest.getOptions().stream()
                .map(optionRequest -> new Option(optionRequest.getText(), question))
                .collect(Collectors.toList());

        question.setOptions(options);
        Question savedQuestion = questionRepository.save(question);

        Long correctAnswerId = options.stream()
                .filter(option -> option.getOptionText().equals(questionRequest.getCorrectAnswer()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Correct answer not found in options"))
                .getId();

        List<OptionResponse> optionResponses = savedQuestion.getOptions().stream()
                .map(option -> new OptionResponse(option.getId(), option.getOptionText()))
                .collect(Collectors.toList());

        QuestionResponse response = new QuestionResponse();
        response.setId(savedQuestion.getId());
        response.setQuestion(savedQuestion.getQuestion());
        response.setCorrectAnswerId(correctAnswerId);
        response.setQuestionTypeId(savedQuestion.getQuestionType().getId());
        response.setQuestionTypeName(savedQuestion.getQuestionType().getName());
        response.setCategoryName(savedQuestion.getCategory().getName());
        response.setDificaltyName(savedQuestion.getDifficulty().getName());
        response.setOptions(optionResponses);
        response.setCreatedBy(savedQuestion.getCreatedBy());
        response.setCreatedTime(savedQuestion.getCreatedTime());

        return response;
    }

    @Transactional(readOnly = true)
    public QuestionResponse getQuestionById(Long id) {
        // Find question by ID, return null if not found
        Question question = questionRepository.findById(id).orElse(null);
        if (question == null) {
            return null; // Return null or a custom empty response object
        }

        // Map options to OptionResponse
        List<OptionResponse> optionResponses = question.getOptions().stream()
                .map(option -> new OptionResponse(option.getId(), option.getOptionText()))
                .collect(Collectors.toList());

        // Find correct answer ID if present
        Long correctAnswerId = question.getOptions().stream()
                .filter(option -> option.getOptionText().equals(question.getCorrectAnswer()))
                .findFirst()
                .map(Option::getId) // Use map to handle Optional gracefully
                .orElse(null); // Return null if not found

        // Build the QuestionResponse object
        QuestionResponse response = new QuestionResponse();
        response.setId(question.getId());
        response.setQuestion(question.getQuestion());
        response.setCorrectAnswerId(correctAnswerId);
        response.setQuestionTypeId(question.getQuestionType().getId());
        response.setQuestionTypeName(question.getQuestionType().getName());
        response.setCategoryId(question.getCategory().getId());
        response.setDifficultyId(question.getDifficulty().getId());
        response.setOptions(optionResponses);
        response.setCategoryName(question.getCategory().getName());
        response.setDificaltyName(question.getDifficulty().getName());
        response.setCreatedBy(question.getCreatedBy());
        response.setCreatedTime(question.getCreatedTime());

        return response; // Return the built response
    }


    public List<QuestionResponse> getAllQuestion() {
        List<Question> questionList = questionRepository.findAll();

        if (questionList.isEmpty()) {
            return new ArrayList<>(); // Return empty list
        }

        List<QuestionResponse> questionResponseList = new ArrayList<>();
        for (Question question : questionList) {
            List<OptionResponse> optionResponses = question.getOptions().stream()
                    .map(option -> new OptionResponse(option.getId(), option.getOptionText()))
                    .collect(Collectors.toList());

            Long correctAnswerId = question.getOptions().stream()
                    .filter(option -> option.getOptionText().equals(question.getCorrectAnswer()))
                    .findFirst()
                    .map(Option::getId) // Use map to handle optional
                    .orElse(null);

            QuestionResponse response = new QuestionResponse();
            response.setId(question.getId());
            response.setQuestion(question.getQuestion());
            response.setCorrectAnswerId(correctAnswerId);
            response.setQuestionTypeId(question.getQuestionType().getId());
            response.setQuestionTypeName(question.getQuestionType().getName());
            response.setCategoryId(question.getCategory().getId());
            response.setDifficultyId(question.getDifficulty().getId());
            response.setOptions(optionResponses);
            response.setCategoryName(question.getCategory().getName());
            response.setDificaltyName(question.getDifficulty().getName());
            response.setCreatedBy(question.getCreatedBy());
            response.setCreatedTime(question.getCreatedTime());
            questionResponseList.add(response);
        }
        return questionResponseList;
    }


    public List<QuestionResponse> findQuestionsByCategoryId(Long categoryId) {
        List<Question> questionList = questionRepository.findByCategoryId(categoryId);

        if (questionList.isEmpty()) {
            return new ArrayList<>(); // Return empty list
        }

        return questionList.stream().map(question -> {
            List<OptionResponse> optionResponses = question.getOptions().stream()
                    .map(option -> new OptionResponse(option.getId(), option.getOptionText()))
                    .collect(Collectors.toList());

            Long correctAnswerId = question.getOptions().stream()
                    .filter(option -> option.getOptionText().equals(question.getCorrectAnswer()))
                    .findFirst()
                    .map(Option::getId) // Use map to handle optional
                    .orElse(null);

            QuestionResponse response = new QuestionResponse();
            response.setId(question.getId());
            response.setQuestion(question.getQuestion());
            response.setCorrectAnswerId(correctAnswerId);
            response.setQuestionTypeId(question.getQuestionType().getId());
            response.setQuestionTypeName(question.getQuestionType().getName());
            response.setCategoryId(question.getCategory().getId());
            response.setDifficultyId(question.getDifficulty().getId());
            response.setOptions(optionResponses);
            response.setCategoryName(question.getCategory().getName());
            response.setDificaltyName(question.getDifficulty().getName());
            response.setCreatedBy(question.getCreatedBy());
            response.setCreatedTime(question.getCreatedTime());
            return response;
        }).collect(Collectors.toList());
    }


    public List<QuestionResponse> findQuestionsByDifficultyId(Long difficultyId) {
        List<Question> questionList = questionRepository.findByDifficultyId(difficultyId);

        if (questionList.isEmpty()) {
            return new ArrayList<>(); // Return empty list
        }

        return questionList.stream().map(question -> {
            List<OptionResponse> optionResponses = question.getOptions().stream()
                    .map(option -> new OptionResponse(option.getId(), option.getOptionText()))
                    .collect(Collectors.toList());

            Long correctAnswerId = question.getOptions().stream()
                    .filter(option -> option.getOptionText().equals(question.getCorrectAnswer()))
                    .findFirst()
                    .map(Option::getId) // Use map to handle optional
                    .orElse(null);

            QuestionResponse response = new QuestionResponse();
            response.setId(question.getId());
            response.setQuestion(question.getQuestion());
            response.setCorrectAnswerId(correctAnswerId);
            response.setQuestionTypeId(question.getQuestionType().getId());
            response.setQuestionTypeName(question.getQuestionType().getName());
            response.setCategoryId(question.getCategory().getId());
            response.setDifficultyId(question.getDifficulty().getId());
            response.setOptions(optionResponses);
            response.setCategoryName(question.getCategory().getName());
            response.setDificaltyName(question.getDifficulty().getName());
            response.setCreatedBy(question.getCreatedBy());
            response.setCreatedTime(question.getCreatedTime());
            return response;
        }).collect(Collectors.toList());
    }

    public List<QuestionResponse> getQuestionByQuestionByType(Long questionTypeId) {


        List<Question> questionList = questionRepository.findByQuestionTypeId(questionTypeId);

        if (questionList.isEmpty()) {
            return new ArrayList<>(); // Return empty list
        }

        return questionList.stream().map(question -> {
            List<OptionResponse> optionResponses = question.getOptions().stream()
                    .map(option -> new OptionResponse(option.getId(), option.getOptionText()))
                    .collect(Collectors.toList());

            Long correctAnswerId = question.getOptions().stream()
                    .filter(option -> option.getOptionText().equals(question.getCorrectAnswer()))
                    .findFirst()
                    .map(Option::getId) // Use map to handle optional
                    .orElse(null);

            QuestionResponse response = new QuestionResponse();
            response.setId(question.getId());
            response.setQuestion(question.getQuestion());
            response.setCorrectAnswerId(correctAnswerId);
            response.setQuestionTypeId(question.getQuestionType().getId());
            response.setQuestionTypeName(question.getQuestionType().getName());
            response.setCategoryId(question.getCategory().getId());
            response.setDifficultyId(question.getDifficulty().getId());
            response.setOptions(optionResponses);
            response.setCategoryName(question.getCategory().getName());
            response.setDificaltyName(question.getDifficulty().getName());
            response.setCreatedBy(question.getCreatedBy());
            response.setCreatedTime(question.getCreatedTime());
            return response;
        }).collect(Collectors.toList());
    }

    public List<QuestionResponse> getQuizQuestionsFromId(List<Long> questionIds) {

        List<Question> questionList = questionRepository.findAllById(questionIds);

        return questionList.stream().map(question -> {
            List<OptionResponse> optionResponses = question.getOptions().stream()
                    .map(option -> new OptionResponse(option.getId(), option.getOptionText()))
                    .collect(Collectors.toList());

            Long correctAnswerId = question.getOptions().stream()
                    .filter(option -> option.getOptionText().equals(question.getCorrectAnswer()))
                    .findFirst()
                    .map(Option::getId)
                    .orElse(null);

            QuestionResponse response = new QuestionResponse();
            response.setId(question.getId());
            response.setQuestion(question.getQuestion());
            response.setCorrectAnswerId(correctAnswerId);
            response.setQuestionTypeId(question.getQuestionType().getId());
            response.setQuestionTypeName(question.getQuestionType().getName());
            response.setCategoryId(question.getCategory().getId());
            response.setDifficultyId(question.getDifficulty().getId());
            response.setOptions(optionResponses);
            response.setCategoryName(question.getCategory().getName());
            response.setDificaltyName(question.getDifficulty().getName());
            response.setCreatedBy(question.getCreatedBy());
            response.setCreatedTime(question.getCreatedTime());
            return response;
        }).collect(Collectors.toList());
    }

    public ResponseEntity<List<Long>> getQestionsForQuiz(Long categoryId, Long difficultyId, Integer numQuestions) {
        List<Long> questions;

        if (categoryId == null && difficultyId == null) {
            questions = questionRepository.findRandomQuestionIds(numQuestions);
        } else if (categoryId == null) {
            questions = questionRepository.findRandomQuestionsByDifficulty(difficultyId, numQuestions);
        } else if (difficultyId == null) {
            questions = questionRepository.findRandomQuestionsByCategory(categoryId, numQuestions);
        } else {
            questions = questionRepository.findRandomQuestionsByCategoryAndDifficulty(categoryId, difficultyId, numQuestions);
        }

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public List<Category> getQuestionCategory() {
        return categoryRepository.findAll();
    }

    public List<Difficulty> getQuestionDifficulty() {

        return  difficultyRepository.findAll();
    }

    public List<QuestionType> getQuestionType() {
        return  questionTypeRepository.findAll();
    }
}
