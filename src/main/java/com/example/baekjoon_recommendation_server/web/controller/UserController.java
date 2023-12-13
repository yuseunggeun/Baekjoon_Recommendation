package com.example.baekjoon_recommendation_server.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.baekjoon_recommendation_server.domain.User;
import com.example.baekjoon_recommendation_server.exception.CustomExceptions;
import com.example.baekjoon_recommendation_server.exception.ResponseMessage;
import com.example.baekjoon_recommendation_server.exception.StatusCode;
import com.example.baekjoon_recommendation_server.repository.UserRepository;
import com.example.baekjoon_recommendation_server.service.UserService;
import com.example.baekjoon_recommendation_server.web.dto.UserRequestDto;
import com.example.baekjoon_recommendation_server.web.dto.UserResponseDto;
import com.example.baekjoon_recommendation_server.web.dto.base.DefaultRes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController{

	private final UserRepository userRepository;
	private final UserService userService;

	@PostMapping("/test") //test api
	public ResponseEntity test(){
		try{
			User user = User.builder().id(1l).userName("test").password("test").build();
			userRepository.save(user);
			return new ResponseEntity(DefaultRes.res(StatusCode.OK, "test success"), HttpStatus.OK);
		} catch (Exception e){
			log.error("error : {}", e);
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody UserRequestDto.LoginDto loginDto){
		try{
			UserResponseDto.LoginResDto res = userService.login(loginDto);

			return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS, res), HttpStatus.OK);
		} catch (CustomExceptions.LoginException e){
			log.error("error : {}", e);
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity signup(@RequestBody UserRequestDto.SignUpDto signUpDto){
		try{
			UserResponseDto.SignUpResDto res = userService.signUp(signUpDto);

			return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.REGISTER_SUCCESS, res), HttpStatus.OK);
		} catch (CustomExceptions.RegisterException e){
			log.error("error : {}", e);
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}
}
