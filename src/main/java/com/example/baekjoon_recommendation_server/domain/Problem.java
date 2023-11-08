package com.example.baekjoon_recommendation_server.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Problem {

	@Id
	private Long id;

	private String title;

	private Integer difficulty;

	private Integer solveCount;

	private boolean bookmarked;
	private boolean solved;
}