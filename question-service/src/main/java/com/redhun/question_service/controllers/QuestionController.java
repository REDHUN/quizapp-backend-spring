package com.redhun.question_service.controllers;
import com.redhun.question_service.dto.QuestionRequest;
import com.redhun.question_service.dto.QuestionResponse;
import com.redhun.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("createQuestion")
    public ResponseEntity<QuestionResponse> createQuestion(@RequestBody QuestionRequest questionRequest) {
        QuestionResponse questionResponse = questionService.createQuestion(questionRequest);
        return ResponseEntity.ok(questionResponse);
    }

    @GetMapping("getQuestionById/{id}")
    public ResponseEntity<QuestionResponse> getQuestionById(@PathVariable Long id) {
        QuestionResponse questionResponse = questionService.getQuestionById(id);
        return ResponseEntity.ok(questionResponse);
    }


    @GetMapping("getAllQuestions")
    public ResponseEntity<List<QuestionResponse> >getAllQuestions() {
        List<QuestionResponse> questionResponseList = questionService.getAllQuestion();
        return ResponseEntity.ok(questionResponseList);
    }

    @GetMapping("getQuestionByCategoryId/{id}")
    public ResponseEntity<List<QuestionResponse> >getQuestionByCategoryId(@PathVariable Long id) {
        List<QuestionResponse> questionResponseList = questionService.findQuestionsByCategoryId(id);
        return ResponseEntity.ok(questionResponseList);
    }
    @GetMapping("getQuestionByDifficultyId/{id}")
    public ResponseEntity<List<QuestionResponse> >etQuestionByDifficultyId(@PathVariable Long id) {
        List<QuestionResponse> questionResponseList = questionService.findQuestionsByDifficultyId(id);
        return ResponseEntity.ok(questionResponseList);
    }
    @GetMapping("getQuestionByQuestionType/{id}")
    public ResponseEntity<List<QuestionResponse>>getQuestionByQuestionByType(@PathVariable Long id) {
        List<QuestionResponse> questionResponseList = questionService.getQuestionByQuestionByType(id);
        return ResponseEntity.ok(questionResponseList);
    }
    // Additional endpoints for CRUD operations
}