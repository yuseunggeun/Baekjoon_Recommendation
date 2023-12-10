package com.example.baekjoon_recommendation_server.web.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class UserResponseDto {

	@Builder
	@Data
	@AllArgsConstructor(access = AccessLevel.PROTECTED)
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class LoginResDto{
		private String userId;
		private String name;
		private String password;
	}

	@Builder
	@Data
	@AllArgsConstructor(access = AccessLevel.PROTECTED)
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SignUpResDto{
		private String userId;
		private String name;
		private String password;
	}
}
