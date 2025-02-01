package com.redhun.quiz_service.controller;

import com.redhun.quiz_service.dto.CreateQuizRequest;
import com.redhun.quiz_service.dto.QuestionResponseDto;
import com.redhun.quiz_service.model.Quiz;
import com.redhun.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("createQuiz")
    public ResponseEntity<Long> createQuiz(@RequestBody CreateQuizRequest quizDto) {

        return quizService.createQuiz(quizDto.getCategoryId(),quizDto.getDifficultyId(), quizDto.getNumberOfQuestions(), quizDto.getTitle(),quizDto.getDescription());
    }

    @GetMapping("getQuizQuestions/{id}")
    public ResponseEntity<List<QuestionResponseDto>> getQuizQuestions(@PathVariable Long id) {
        return quizService.getQuizQuestions(id);
    }
    @GetMapping("getAllQuiz")
    public ResponseEntity<List<Quiz>> getQuizQuestions() {
        return quizService.getAllQuiz();
    }
//
//    @PostMapping("submit/{id}")
//    public  ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
//
//        return  quizService.calculateResult(id,responses);
//    }
}
