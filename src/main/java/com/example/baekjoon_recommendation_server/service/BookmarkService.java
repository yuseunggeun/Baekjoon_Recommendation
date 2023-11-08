package com.example.baekjoon_recommendation_server.service;

import org.springframework.stereotype.Service;

import com.example.baekjoon_recommendation_server.repository.BookmarkRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookmarkService {
	private final BookmarkRepository bookmarkRepository;


}
