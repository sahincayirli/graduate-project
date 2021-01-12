package com.graduateproject.jwtimplementation.repository;

import com.graduateproject.jwtimplementation.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProblemRepository extends JpaRepository<Problem,Long> {
    Optional<Problem> findByName(String name);
}
