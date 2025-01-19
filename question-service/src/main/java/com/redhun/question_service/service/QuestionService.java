package com.redhun.question_service.service;

import com.redhun.question_service.dto.OptionResponse;
import com.redhun.question_service.dto.QuestionRequest;
import com.redhun.question_service.dto.QuestionResponse;
import com.redhun.question_service.models.Category;
import com.redhun.question_service.models.Difficulty;
import com.redhun.question_service.models.Option;
import com.redhun.question_service.models.Question;
import com.redhun.question_service.repository.CategoryRepository;
import com.redhun.question_service.repository.DifficultyRepository;
import com.redhun.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DifficultyRepository difficultyRepository;

    @Transactional
    public QuestionResponse createQuestion(QuestionRequest questionRequest) {
        Category category = categoryRepository.findById(questionRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Difficulty difficulty = difficultyRepository.findById(questionRequest.getDifficultyId())
                .orElseThrow(() -> new RuntimeException("Difficulty not found"));

        Question question = new Question();
        question.setQuestion(questionRequest.getQuestion());
        question.setCorrectAnswer(questionRequest.getCorrectAnswer());  // This will be used to find the correct answer ID
        question.setQuestionType(questionRequest.getQuestionType());
        question.setCategory(category);
        question.setDifficulty(difficulty);

        List<Option> options = questionRequest.getOptions().stream()
                .map(optionRequest -> new Option(optionRequest.getText(), question))
                .collect(Collectors.toList());

        question.setOptions(options);
        Question savedQuestion = questionRepository.save(question);

        // Find the correct answer ID
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
        response.setQuestionType(savedQuestion.getQuestionType());
        response.setCategoryName(savedQuestion.getCategory().toString());
        response.setDifficultyName(savedQuestion.getDifficulty().toString());
        response.setOptions(optionResponses);

        return response;
    }

    @Transactional(readOnly = true)
    public QuestionResponse getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        List<OptionResponse> optionResponses = question.getOptions().stream()
                .map(option -> new OptionResponse(option.getId(), option.getOptionText()))
                .collect(Collectors.toList());

        // Find the correct answer ID
        Long correctAnswerId = question.getOptions().stream()
                .filter(option -> option.getOptionText().equals(question.getCorrectAnswer()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Correct answer not found in options"))
                .getId();

        QuestionResponse response = new QuestionResponse();
        response.setId(question.getId());
        response.setQuestion(question.getQuestion());
        response.setCorrectAnswerId(correctAnswerId);
        response.setQuestionType(question.getQuestionType());
        response.setCategoryName(question.getCategory().toString());
        response.setDifficultyName(question.getDifficulty().toString());
        response.setOptions(optionResponses);

        return response;
    }
}
