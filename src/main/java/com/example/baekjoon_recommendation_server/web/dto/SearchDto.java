package com.example.baekjoon_recommendation_server.web.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class SearchDto {

	private Integer minDifficulty;
	private Integer maxDifficulty;
	private Integer minSolveCount;
	private Integer maxSolveCount;
	private String logical; //and, or
	private List<String> tags;
}
