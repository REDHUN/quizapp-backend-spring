package com.redhun.question_service.repository;

import com.redhun.question_service.models.Category;
import com.redhun.question_service.models.Difficulty;
import com.redhun.question_service.models.Question;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM question ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Question> findRandomQuestions(@Param("limit") int limit);


    List<Question> findByCategoryId(Long categoryId);

    List<Question> findByDifficultyId(Long difficultyId);

    List<Question> findByQuestionTypeId(Long questionTypeId);
}
