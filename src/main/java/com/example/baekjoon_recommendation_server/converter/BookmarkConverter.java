package com.example.baekjoon_recommendation_server.converter;

import org.springframework.stereotype.Component;

import com.example.baekjoon_recommendation_server.domain.Bookmark;
import com.example.baekjoon_recommendation_server.domain.User;
import com.example.baekjoon_recommendation_server.web.dto.BookmarkDto;
import com.example.baekjoon_recommendation_server.web.dto.ProblemDetailDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookmarkConverter {
	public BookmarkDto toBookmarkDto(Long id, String title, Integer difficulty, Integer solveCount, String memo){
		return BookmarkDto.builder()
			.id(id)
			.title(title)
			.difficulty(difficulty)
			.solveCount(solveCount)
			.memo(memo)
			.build();
	}

	public Bookmark problemToBookmarkDto(User user, ProblemDetailDto problemDetailDto){
		return Bookmark.builder()
			.user(user)
			.problemId(problemDetailDto.getId())
			.title(problemDetailDto.getTitle())
			.difficulty(problemDetailDto.getDifficulty()) //나중에 난이도 긁어오는 로직 필요
			.solveCount(problemDetailDto.getSolveCount())
			.memo("Empty")
			.build();
	}

}
