package com.example.baekjoon_recommendation_server.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
public class Solved {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private User user;
	private Long problemId;

	private String title;
	private Integer difficulty;
	private Integer solveCount;
	private String memo;
	@CreatedDate
	@Column(columnDefinition = "datetime default now()", updatable = false)
	private LocalDateTime solvedTime;
	private Long elapsedTime;

	public void updateMemo(String memo){
		this.memo = memo;
	}

	@PrePersist
	public void prePersist() {
		if (solvedTime == null) {
			solvedTime = LocalDateTime.now();
		}
	}
}
