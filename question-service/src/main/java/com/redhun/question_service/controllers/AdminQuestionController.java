package com.redhun.question_service.controllers;

import com.redhun.question_service.dto.QuestionResponse;
import com.redhun.question_service.dto.edit_question_dto.EditQuestionDto;
import com.redhun.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
