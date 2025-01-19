package com.redhun.question_service.repository;

import com.redhun.question_service.models.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DifficultyRepository extends JpaRepository<Difficulty, Long> {
    Optional<Difficulty> findById(Long id);
}