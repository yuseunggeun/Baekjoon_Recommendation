package com.example.baekjoon_recommendation_server.web.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProblemDto {

	private Long id;
	private String title;
	private Integer difficulty;
	private List<String> tags;
	private Boolean bookmarked;
	private Boolean solved;
}
