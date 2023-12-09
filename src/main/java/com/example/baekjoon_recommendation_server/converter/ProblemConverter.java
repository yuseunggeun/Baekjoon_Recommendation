package com.example.baekjoon_recommendation_server.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.baekjoon_recommendation_server.web.dto.ProblemDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProblemConverter {
	public ProblemDto toProblemDto(Long id, String title, Integer difficulty, List<String> tags){
		return ProblemDto.builder()
			.id(id)
			.title(title)
			.difficulty(difficulty)
			.tags(tags)
			.build();
	}
}
