package com.example.baekjoon_recommendation_server.web.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.example.baekjoon_recommendation_server.domain.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SolvedDto {
	private Long id;
	private Long problemId;
	private LocalDateTime solvedTime;
	private Long elapsedTime;
	private String memo;
}
