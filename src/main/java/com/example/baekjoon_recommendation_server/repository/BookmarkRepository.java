package com.example.baekjoon_recommendation_server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.baekjoon_recommendation_server.domain.Bookmark;
import com.example.baekjoon_recommendation_server.domain.User;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

	List<Bookmark> findByUser(User user);
}
