package com.example.baekjoon_recommendation_server.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.baekjoon_recommendation_server.exception.ResponseMessage;
import com.example.baekjoon_recommendation_server.exception.StatusCode;
import com.example.baekjoon_recommendation_server.repository.SolvedRepository;
import com.example.baekjoon_recommendation_server.web.dto.BookmarkDto;
import com.example.baekjoon_recommendation_server.web.dto.SolvedDto;
import com.example.baekjoon_recommendation_server.web.dto.base.DefaultRes;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/solved")
public class SolvedController {
	SolvedRepository solvedRepository;
	@GetMapping
	public ResponseEntity solves(){
		try{
			Long userId = 1l;
			//List<SolvedDto> solvedDtoList = //bookmarkService.getBookmarks(userId);
			return new ResponseEntity(
				DefaultRes.res(StatusCode.OK, "해결문제 불러오기 성공"/*, solvedDtoList*/), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}
}
