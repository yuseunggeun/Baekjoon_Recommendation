package com.example.baekjoon_recommendation_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.baekjoon_recommendation_server.domain.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

	//List<Problem> findByDifficultyAndInteger();
}
