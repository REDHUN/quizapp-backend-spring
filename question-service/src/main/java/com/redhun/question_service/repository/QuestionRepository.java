package com.redhun.question_service.repository;

import com.redhun.question_service.models.Category;
import com.redhun.question_service.models.Difficulty;
import com.redhun.question_service.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q WHERE q.isDeleted = 'N'")
    List<Question> findAll();

    @Query(value = "SELECT * FROM question WHERE is_deleted = 'N' ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Question> findRandomQuestions(int limit);

    @Query("SELECT q FROM Question q WHERE q.isDeleted = 'N' AND q.category.id = :categoryId")
    List<Question> findByCategoryId(Long categoryId);

    @Query("SELECT q FROM Question q WHERE q.isDeleted = 'N' AND q.difficulty.id = :difficultyId")
    List<Question> findByDifficultyId(Long difficultyId);

    @Query("SELECT q FROM Question q WHERE q.isDeleted = 'N' AND q.questionType.id = :questionTypeId")
    List<Question> findByQuestionTypeId(Long questionTypeId);

    @Query(value = "SELECT q.id FROM question q WHERE q.category_id = :categoryId AND q.difficulty_id = :difficultyId AND q.is_deleted = 'N' ORDER BY RANDOM() LIMIT :numQuestions", nativeQuery = true)
    List<Long> findRandomQuestionsByCategoryAndDifficulty(Long categoryId, Long difficultyId, Integer numQuestions);

    @Query(value = "SELECT q.id FROM Question q WHERE q.category_id = :categoryId AND q.is_deleted = 'N' ORDER BY RANDOM() LIMIT :numQuestions", nativeQuery = true)
    List<Long> findRandomQuestionsByCategory(Long categoryId, Integer numQuestions);

    @Query(value = "SELECT q.id FROM Question q WHERE q.difficulty_id = :difficultyId AND q.is_deleted = 'N' ORDER BY RANDOM() LIMIT :numQuestions", nativeQuery = true)
    List<Long> findRandomQuestionsByDifficulty(Long difficultyId, Integer numQuestions);

    @Query(value = "SELECT q.id FROM Question q WHERE q.is_deleted = 'N' ORDER BY RANDOM() LIMIT :numQuestions", nativeQuery = true)
    List<Long> findRandomQuestionIds(Integer numQuestions);

    // New methods for filtering
    @Query("SELECT q FROM Question q WHERE " +
            "(:categoryId IS NULL OR q.category.id = :categoryId) AND " +
            "(:questionTypeId IS NULL OR q.questionType.id = :questionTypeId) AND " +
            "(:difficultyId IS NULL OR q.difficulty.id = :difficultyId) AND " +
            "q.isDeleted = 'N'")
    List<Question> findByFilters(
            @Param("categoryId") Long categoryId,
            @Param("questionTypeId") Long questionTypeId,
            @Param("difficultyId") Long difficultyId
    );

    @Query("SELECT q FROM Question q WHERE " +
            "q.category.id = :categoryId AND " +
            "q.questionType.id = :questionTypeId AND " +
            "q.difficulty.id = :difficultyId AND " +
            "q.isDeleted = 'N'")
    List<Question> findByCategoryIdAndQuestionTypeIdAndDifficultyId(
            @Param("categoryId") Long categoryId,
            @Param("questionTypeId") Long questionTypeId,
            @Param("difficultyId") Long difficultyId
    );
}