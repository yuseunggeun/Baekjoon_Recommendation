package com.example.baekjoon_recommendation_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.baekjoon_recommendation_server.domain.Problem;
import com.example.baekjoon_recommendation_server.domain.Solved;

public interface SolvedRepository extends JpaRepository<Solved, Long> {
}
