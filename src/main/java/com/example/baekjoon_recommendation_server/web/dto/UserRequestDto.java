package com.example.baekjoon_recommendation_server.web.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestDto {

	@Builder
	@Getter
	@AllArgsConstructor(access = AccessLevel.PROTECTED)
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SignUpDto{
		private String userId;
		private String name;
		private String password;
	}

	@Builder
	@Getter
	@AllArgsConstructor(access = AccessLevel.PROTECTED)
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class LoginDto{
		private String userId;
		private String password;
	}

	@Builder
	@Getter
	@AllArgsConstructor(access = AccessLevel.PROTECTED)
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class ValidateDto{
		private String userId;
		private String password;
	}
}
