package com.example.baekjoon_recommendation_server.web.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SolvedRequestDto {
	private Long problemId;
	private String title;
	private Integer difficulty;
	private Long elapsedTime;
}
