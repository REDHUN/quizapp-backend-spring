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

    @Query(value = "SELECT q.id FROM question q WHERE q.category_id = :categoryId AND q.difficulty_id = :difficultyId ORDER BY RANDOM() LIMIT :numQuestions", nativeQuery = true)
    List<Long> findRandomQuestionsByCatogory(Long categoryId, Long difficultyId, Integer numQuestions);

    @Query(value = "SELECT q.id FROM Question q WHERE q.category_id = :categoryId AND q.difficulty_id = :difficultyId ORDER BY RANDOM() LIMIT :numQuestions", nativeQuery = true)
    List<Long> findRandomQuestionsByCategoryAndDifficulty(Long categoryId, Long difficultyId, Integer numQuestions);

    @Query(value = "SELECT q.id FROM Question q WHERE q.category_id = :categoryId ORDER BY RANDOM() LIMIT :numQuestions", nativeQuery = true)
    List<Long> findRandomQuestionsByCategory(Long categoryId, Integer numQuestions);

    @Query(value = "SELECT q.id FROM Question q WHERE q.difficulty_id = :difficultyId ORDER BY RANDOM() LIMIT :numQuestions", nativeQuery = true)
    List<Long> findRandomQuestionsByDifficulty(Long difficultyId, Integer numQuestions);

    @Query(value = "SELECT q.id FROM Question q ORDER BY RANDOM() LIMIT :numQuestions", nativeQuery = true)
    List<Long> findRandomQuestionIds(Integer numQuestions);
}

