package com.redhun.quiz_service.controller;

import com.redhun.quiz_service.dto.ResultResponseDto;
import com.redhun.quiz_service.dto.UserResponseDto;
import com.redhun.quiz_service.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/quiz/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping("/calculate")
    public ResponseEntity<ResultResponseDto> getQuizResult(
            @RequestBody UserResponseDto userResponses) {
        ResultResponseDto result = resultService.calculateResult(userResponses);
        return ResponseEntity.ok(result);
    }
}