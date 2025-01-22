package com.redhun.quiz_service.feign;

import com.redhun.quiz_service.dto.QuestionResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {



    @GetMapping("api/questions/generateQuiz")
    public ResponseEntity<List<Long>> getQuestionsForQuiz(@RequestParam Long categoryId,@RequestParam Long difficultyId,  @RequestParam Integer numQuestions);



    @PostMapping("question/getQuestion")
    public  ResponseEntity<List<QuestionResponseDto>> getQuestionsFromId(@RequestBody List<Integer> questionIds);
//
//    @PostMapping("question/getScore")
//    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
