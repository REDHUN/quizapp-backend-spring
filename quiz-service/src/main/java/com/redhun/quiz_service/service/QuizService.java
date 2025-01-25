package com.redhun.quiz_service.service;

import com.redhun.quiz_service.dto.QuestionResponseDto;
import com.redhun.quiz_service.feign.QuizInterface;
import com.redhun.quiz_service.model.Quiz;
import com.redhun.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepo;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(Long categoryId, Long difficultyId, int numQ, String title, String description) {
        List<Long> questions;

        // Fetch questions based on the provided criteria
        if (categoryId == null && difficultyId == null) {
            questions = quizInterface.getQuestionsForQuiz(null, null, numQ).getBody();
        } else if (categoryId == null) {
            questions = quizInterface.getQuestionsForQuiz(null, difficultyId, numQ).getBody();
        } else if (difficultyId == null) {
            questions = quizInterface.getQuestionsForQuiz(categoryId, null, numQ).getBody();
        } else {
            questions = quizInterface.getQuestionsForQuiz(categoryId, difficultyId, numQ).getBody();
        }

        // Create and save the quiz
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setDescription(description);
        quiz.setQuestionIds(questions);
        quiz.setTotalQuestions(numQ);
        quizRepo.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<List<Quiz>> getAllQuiz() {

       List<Quiz> quizList=quizRepo.findAll();

       if(quizList.isEmpty()){
           return ResponseEntity.noContent().build();

       }
       else{
           return new  ResponseEntity<>(quizList,HttpStatus.OK);
       }

    }

    public ResponseEntity<Quiz> getQuiz(Long quizId) {

        Optional<Quiz> quizOptional = quizRepo.findById(quizId);

        if(quizOptional.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            Quiz quiz = quizOptional.get();
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        }
    }

    public ResponseEntity<List<QuestionResponseDto>> getQuizQuestions(Long id) {

        Quiz quiz=quizRepo.findById(id).orElse(null);

        if(quiz!=null){
            List<Long>questionIds=quiz.getQuestionIds();
            return quizInterface.getQuestionsFromId(questionIds);
        }
        else{
            return ResponseEntity.noContent().build();
        }

    }

}
