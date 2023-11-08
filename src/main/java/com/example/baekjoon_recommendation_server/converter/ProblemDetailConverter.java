package com.example.baekjoon_recommendation_server.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.baekjoon_recommendation_server.web.dto.ProblemDetailDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProblemDetailConverter {

	public ProblemDetailDto toProblemDetail(Long id, String title, Integer solveCount,
		String content, String inputRule, String outputRule,
		List<String> sample, boolean bookmarked, boolean solved){

		return ProblemDetailDto.builder()
			.id(id)
			.title(title)
			.solveCount(solveCount)
			.content(content)
			.inputRule(inputRule)
			.outputRule(outputRule)
			.samples(sample)
			.bookmarked(bookmarked)
			.solved(solved)
			.build();
	}
}
