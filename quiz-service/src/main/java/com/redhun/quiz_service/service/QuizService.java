package com.redhun.quiz_service.service;

import com.redhun.quiz_service.feign.QuizInterface;
import com.redhun.quiz_service.model.Quiz;
import com.redhun.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepo;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(Long categoryId,Long difficultyId , int numQ, String title,String description) {

        List<Long> questions = quizInterface.getQuestionsForQuiz(categoryId,difficultyId, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setDescription(description);
        quiz.setQuestionIds(questions);
        quiz.setTotalQuestions(numQ);
        quizRepo.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }
}
