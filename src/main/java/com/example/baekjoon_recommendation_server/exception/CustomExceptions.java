package com.example.baekjoon_recommendation_server.exception;

public class CustomExceptions {

	public static class LoginException extends RuntimeException{
		public LoginException(String message){
			super(message);
		}
	}

	public static class RegisterException extends RuntimeException{
		public RegisterException(String message){
			super(message);
		}
	}
}
