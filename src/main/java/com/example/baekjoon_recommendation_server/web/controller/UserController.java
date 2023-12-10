package com.example.baekjoon_recommendation_server.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.baekjoon_recommendation_server.domain.User;
import com.example.baekjoon_recommendation_server.exception.ResponseMessage;
import com.example.baekjoon_recommendation_server.exception.StatusCode;
import com.example.baekjoon_recommendation_server.repository.UserRepository;
import com.example.baekjoon_recommendation_server.web.dto.BookmarkDto;
import com.example.baekjoon_recommendation_server.web.dto.base.DefaultRes;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController{

	private final UserRepository userRepository;

	@PostMapping("/test")
	public ResponseEntity test(){
		try{
			User user = User.builder().id(1l).userName("test").password("test").build();
			userRepository.save(user);
			return new ResponseEntity(
				DefaultRes.res(StatusCode.OK, "test success"), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/login")
	public ResponseEntity login(){
		try{
			User user = User.builder().id(1l).userName("test").password("test").build();
			userRepository.save(user);
			return new ResponseEntity(
				DefaultRes.res(StatusCode.OK, "test success"), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/singup")
	public ResponseEntity signup(){
		try{
			User user = User.builder().id(1l).userName("test").password("test").build();
			userRepository.save(user);
			return new ResponseEntity(
				DefaultRes.res(StatusCode.OK, "test success"), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}
}
