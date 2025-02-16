package com.redhun.question_service.controllers;

import com.redhun.question_service.dto.QuestionRequest;
import com.redhun.question_service.dto.QuestionResponse;
import com.redhun.question_service.models.Category;
import com.redhun.question_service.models.Difficulty;
import com.redhun.question_service.models.QuestionType;
import com.redhun.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<QuestionResponse>> getAllQuestions() {
        List<QuestionResponse> questionResponseList = questionService.getAllQuestion();
        return ResponseEntity.ok(questionResponseList);
    }

    @GetMapping("getQuestionByCategoryId/{id}")
    public ResponseEntity<List<QuestionResponse>> getQuestionByCategoryId(@PathVariable Long id) {
        List<QuestionResponse> questionResponseList = questionService.findQuestionsByCategoryId(id);
        return ResponseEntity.ok(questionResponseList);
    }

    @GetMapping("getQuestionByDifficultyId/{id}")
    public ResponseEntity<List<QuestionResponse>> etQuestionByDifficultyId(@PathVariable Long id) {
        List<QuestionResponse> questionResponseList = questionService.findQuestionsByDifficultyId(id);
        return ResponseEntity.ok(questionResponseList);
    }

    @GetMapping("getQuestionByQuestionType/{id}")
    public ResponseEntity<List<QuestionResponse>> getQuestionByQuestionByType(@PathVariable Long id) {
        List<QuestionResponse> questionResponseList = questionService.getQuestionByQuestionByType(id);
        return ResponseEntity.ok(questionResponseList);
    }


    @GetMapping("generateQuiz")
    public ResponseEntity<List<Long>> getQuestionsForQuiz(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long difficultyId,
            @RequestParam Integer numQuestions) {

        return questionService.getQestionsForQuiz(categoryId, difficultyId, numQuestions);
    }


    @PostMapping("getQuizQuestion")

    public ResponseEntity<List<QuestionResponse>> getQuizQuestionsFromId(@RequestBody List<Long> questionIds) {


        return ResponseEntity.ok(questionService.getQuizQuestionsFromId(questionIds));
    }

    //Get Category
    @GetMapping("getQuestionCategory")

    public ResponseEntity<List<Category>> getQuestionCategory() {


        return ResponseEntity.ok(questionService.getQuestionCategory());
    }

    //Delete Category
    @DeleteMapping("deleteCategory/{id}")
    public boolean deleteCategory(@PathVariable Long id) {
        return questionService.deleteCategory(id);
    }
//Check Category Is Used
    @GetMapping("/isCategoryUsed/{id}")
    public boolean isCategoryUsed(@PathVariable Long id) {
        return questionService.isCategoryUsed(id);
    }


    @GetMapping("getQuestionDifficulty")

    public ResponseEntity<List<Difficulty>> getQuestionDifficultyList() {


        return ResponseEntity.ok(questionService.getQuestionDifficulty());
    }

    @GetMapping("getQuestionType")

    public ResponseEntity<List<QuestionType>> getQuestionType() {


        return ResponseEntity.ok(questionService.getQuestionType());
    }

    @GetMapping("filterQuestion")
    public List<QuestionResponse> getQuestionsByFilters(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long questionTypeId,
            @RequestParam(required = false) Long difficultyId) {

        return questionService.getQuestionsByFilters(categoryId, questionTypeId, difficultyId);
    }
}