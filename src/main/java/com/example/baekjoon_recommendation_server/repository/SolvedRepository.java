package com.example.baekjoon_recommendation_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.baekjoon_recommendation_server.domain.Solved;
import com.example.baekjoon_recommendation_server.domain.User;

public interface SolvedRepository extends JpaRepository<Solved, Long> {
	List<Solved> findByUserAndProblemId(User user, Long problemId);
}
