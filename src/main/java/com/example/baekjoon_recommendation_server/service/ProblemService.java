package com.example.baekjoon_recommendation_server.service;

import org.springframework.stereotype.Service;

import com.example.baekjoon_recommendation_server.converter.SearchConverter;
import com.example.baekjoon_recommendation_server.repository.ProblemRepository;
import com.example.baekjoon_recommendation_server.web.dto.SearchDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProblemService {
	private final ProblemRepository problemRepository;
	private final SearchConverter searchConverter;

	public String getSearchQuery(SearchDto searchDto){
		return searchConverter.toSearchQuery(searchDto);
	}
}
