package com.redhun.question_service.controllers;

import com.redhun.question_service.dto.QuestionRequest;
import com.redhun.question_service.dto.QuestionResponse;
import com.redhun.question_service.models.Question;
import com.redhun.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<QuestionResponse> createQuestion(@RequestBody QuestionRequest questionRequest) {
        QuestionResponse questionResponse = questionService.createQuestion(questionRequest);
        return ResponseEntity.ok(questionResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> getQuestionById(@PathVariable Long id) {
        QuestionResponse questionResponse = questionService.getQuestionById(id);
        return ResponseEntity.ok(questionResponse);
    }

//    @GetMapping("getAllQuestions")
//    public ResponseEntity<List<Question>> getAllQuestions(@RequestBody Question question) {
//        return ResponseEntity.status(HttpStatus.FOUND).body(questionService.getAllQuestions());
//    }

//    @GetMapping("/random/{count}")
//    public ResponseEntity<List<Question>> getRandomQuestions(@PathVariable int count) {
//        return ResponseEntity.ok(questionService.getRandomQuestions(count));
//    }

    // Additional endpoints for CRUD operations
}