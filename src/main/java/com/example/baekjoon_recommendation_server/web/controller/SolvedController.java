package com.example.baekjoon_recommendation_server.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.baekjoon_recommendation_server.exception.ResponseMessage;
import com.example.baekjoon_recommendation_server.exception.StatusCode;
import com.example.baekjoon_recommendation_server.repository.SolvedRepository;
import com.example.baekjoon_recommendation_server.service.SolvedService;
import com.example.baekjoon_recommendation_server.service.UserService;
import com.example.baekjoon_recommendation_server.web.dto.MemoDto;
import com.example.baekjoon_recommendation_server.web.dto.SolvedDto;
import com.example.baekjoon_recommendation_server.web.dto.SolvedRequestDto;
import com.example.baekjoon_recommendation_server.web.dto.UserRequestDto;
import com.example.baekjoon_recommendation_server.web.dto.UserResponseDto;
import com.example.baekjoon_recommendation_server.web.dto.base.DefaultRes;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/solved")
public class SolvedController {
	private final UserService userService;
	private final SolvedService solvedService;
	@GetMapping("/solve")
	public ResponseEntity getSolves(@RequestHeader("userId") String userId, @RequestHeader("password") String password){
		try{
			UserRequestDto.ValidateDto req = UserRequestDto.ValidateDto.builder()
				.userId(userId)
				.password(password)
				.build();
			UserResponseDto.ValidateResDto user = userService.getCurrentUser(req);

			List<SolvedDto> solvedDtoList = solvedService.getsolves(user.getUserId());
			return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.SOLVED_READ_SUCCESS, solvedDtoList), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/{problemId}")
	public ResponseEntity addSolved(@PathVariable Long problemId, @RequestHeader("userId") String userId, @RequestHeader("password") String password,
		@RequestBody SolvedRequestDto request){
		try{
			UserRequestDto.ValidateDto req = UserRequestDto.ValidateDto.builder()
				.userId(userId)
				.password(password)
				.build();
			UserResponseDto.ValidateResDto user = userService.getCurrentUser(req);

			solvedService.addSolved(user.getUserId(), request);

			return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.SOLVED_ADD_SUCCESS), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{solvedId}")
	public ResponseEntity updateSolvedMemo(@PathVariable Long solvedId, @RequestBody MemoDto request, @RequestHeader("userId") String userId, @RequestHeader("password") String password){
		try{
			UserRequestDto.ValidateDto req = UserRequestDto.ValidateDto.builder()
				.userId(userId)
				.password(password)
				.build();
			UserResponseDto.ValidateResDto user = userService.getCurrentUser(req);

			String memo = request.getMemo();
			solvedService.updateSolved(solvedId, memo);
			return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.SOLVED_UPDATE_SUCCESS), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}
}
