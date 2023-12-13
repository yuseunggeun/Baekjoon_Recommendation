package com.example.baekjoon_recommendation_server.converter;

import org.springframework.stereotype.Component;

import com.example.baekjoon_recommendation_server.domain.Bookmark;
import com.example.baekjoon_recommendation_server.domain.Solved;
import com.example.baekjoon_recommendation_server.domain.User;
import com.example.baekjoon_recommendation_server.web.dto.BookmarkRequestDto;
import com.example.baekjoon_recommendation_server.web.dto.SolvedDto;
import com.example.baekjoon_recommendation_server.web.dto.SolvedRequestDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SolvedConverter {

	public SolvedDto toSolvedDto(Solved solved){
		return SolvedDto.builder()
			.id(solved.getId())
			.problemId(solved.getProblemId())
			.title(solved.getTitle())
			.difficulty(solved.getDifficulty())
			.solvedTime(solved.getSolvedTime())
			.elapsedTime(solved.getElapsedTime())
			.memo("empty")
			.build();
	}

	public Solved requestDtoToSolved(User user, SolvedRequestDto solvedRequestDto){
		return Solved.builder()
			.user(user)
			.problemId(solvedRequestDto.getProblemId())
			.title(solvedRequestDto.getTitle())
			.difficulty(solvedRequestDto.getDifficulty())
			.elapsedTime(solvedRequestDto.getElapsedTime())
			.memo("Empty")
			.build();
	}
}
