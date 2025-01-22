package com.redhun.question_service.repository;

import com.redhun.question_service.models.Category;
import com.redhun.question_service.models.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionTypeRepository extends JpaRepository<QuestionType, Long> {
    Optional<QuestionType> findById(Long id);

}
