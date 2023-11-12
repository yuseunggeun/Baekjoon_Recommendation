package com.example.baekjoon_recommendation_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.baekjoon_recommendation_server.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
