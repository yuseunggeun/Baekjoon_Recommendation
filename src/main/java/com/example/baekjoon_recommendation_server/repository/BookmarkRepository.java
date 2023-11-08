package com.example.baekjoon_recommendation_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.baekjoon_recommendation_server.domain.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	//
}
