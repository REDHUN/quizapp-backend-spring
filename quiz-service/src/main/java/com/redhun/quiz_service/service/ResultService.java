package com.redhun.quiz_service.service;

import com.redhun.quiz_service.dto.QuestionResponseDto;
import com.redhun.quiz_service.dto.ResultResponseDto;
import com.redhun.quiz_service.dto.UserAnswerResponse;
import com.redhun.quiz_service.dto.UserResponseDto;
import com.redhun.quiz_service.feign.QuizInterface;
import com.redhun.quiz_service.model.Result;
import com.redhun.quiz_service.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResultService {

    @Autowired
    QuizInterface quizInterface;

    @Autowired
    private ResultRepository resultRepository;

    public ResultResponseDto calculateResult(UserResponseDto userResponses) {
        // Extract question IDs
        List<Long> questionIds = userResponses.getUserAnswers()
                .stream()
                .map(UserAnswerResponse::getQuestionId)
                .toList();

        // Fetch questions from Question Service
        List<QuestionResponseDto> questions = quizInterface.getQuestionsFromId(questionIds).getBody();
        if (questions == null) {
            throw new RuntimeException("Failed to fetch questions from the service.");
        }

        // Create a map for quick look-up of correct answers
        Map<Long, String> questionAnswerMap = questions.stream()
                .collect(Collectors.toMap(QuestionResponseDto::getId, q -> String.valueOf(q.getCorrectAnswerId())));

        int score = 0;
        List<Long> userAnswers = new ArrayList<>();
        for (UserAnswerResponse answerResponse : userResponses.getUserAnswers()) {
            Long questionId = answerResponse.getQuestionId();
            Long userAnswer = answerResponse.getUserAnswer();

            userAnswers.add(userAnswer);
            // Ensure proper comparison of IDs and answers using the map
            if (questionAnswerMap.containsKey(questionId) &&
                    String.valueOf(userAnswer).equals(questionAnswerMap.get(questionId))) {
                score++;
            }
        }

        // Save Result
        Result result = new Result(userResponses.getUserId(), userResponses.getQuizId(), score, questionIds, userAnswers);
        resultRepository.save(result);

        return new ResultResponseDto(userResponses.getUserId(), userResponses.getQuizId(), score);
    }
}
