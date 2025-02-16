package com.redhun.question_service.controllers;

import com.redhun.question_service.dto.QuestionResponse;
import com.redhun.question_service.dto.edit_question_dto.EditQuestionDto;
import com.redhun.question_service.models.Category;
import com.redhun.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/questions")
public class AdminQuestionController {
    @Autowired
    QuestionService questionService;

    @PutMapping("/update/{id}")
    public ResponseEntity<QuestionResponse> updateQuestion(@PathVariable Long id, @RequestBody EditQuestionDto questionRequest) {
        QuestionResponse updatedQuestion = questionService.editQuestion(id, questionRequest);
        return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
    }
    @DeleteMapping("/deleteQuestion/{id}")
    public ResponseEntity<Long> deleteQuestion(@PathVariable Long id) {
        Long deletedQuestionId = questionService.deleteQuestion(id);
        if (deletedQuestionId != null) {
            return ResponseEntity.ok(deletedQuestionId); // Return the deleted question ID
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if question is not found
        }
    }
    @PostMapping("/saveCategory")
    public Category saveCategory(@RequestBody Map<String, Object> requestBody) {
        String categoryName = (String) requestBody.get("category");
        String isActive = (String) requestBody.get("isActive");
        Long categoryId = null;
        if (requestBody.get("categoryId") != null) {
            try {
                if (requestBody.get("categoryId") instanceof Number) {
                    categoryId = ((Number) requestBody.get("categoryId")).longValue();
                } else {
                    categoryId = Long.parseLong((String) requestBody.get("categoryId"));
                }
            } catch (NumberFormatException e) {
                // Handle the exception if categoryId is not a valid number
                throw new IllegalArgumentException("Invalid categoryId format");
            }
        }
        return questionService.saveCategory(categoryName, isActive, categoryId);
    }
}
