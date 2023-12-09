package com.example.baekjoon_recommendation_server.web.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder @Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProblemDetailDto {
	private Long id;
	private String title;
	private Integer difficulty;
	private Integer solveCount;
	private String content;
	private String inputRule;
	private String outputRule;
	private List<String> samples;
	private Boolean bookmarked;
	private Boolean solved;
}
