package com.example.baekjoon_recommendation_server.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.baekjoon_recommendation_server.converter.SolvedConverter;
import com.example.baekjoon_recommendation_server.domain.Bookmark;
import com.example.baekjoon_recommendation_server.domain.Solved;
import com.example.baekjoon_recommendation_server.domain.User;
import com.example.baekjoon_recommendation_server.exception.CustomExceptions;
import com.example.baekjoon_recommendation_server.repository.SolvedRepository;
import com.example.baekjoon_recommendation_server.repository.UserRepository;
import com.example.baekjoon_recommendation_server.web.dto.BookmarkDto;
import com.example.baekjoon_recommendation_server.web.dto.BookmarkRequestDto;
import com.example.baekjoon_recommendation_server.web.dto.SolvedDto;
import com.example.baekjoon_recommendation_server.web.dto.SolvedRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SolvedService {
	private final UserRepository userRepository;
	private final SolvedRepository solvedRepository;
	private final SolvedConverter solvedConverter;

	public List<SolvedDto> getsolves(String userId){
		User user = userRepository.findByUserId(userId);

		List<Solved> solves = user.getSolves();
		List<SolvedDto> solvedDtos = new ArrayList<SolvedDto>();
		for(Solved solved : solves){
			solvedDtos.add(solvedConverter.toSolvedDto(solved));
		}
		return solvedDtos;
	}

	public void addSolved(String userId, SolvedRequestDto solvedRequestDto){
		User user = userRepository.findByUserId(userId);

		List<Solved> solves = solvedRepository.findByUserAndProblemId(user, solvedRequestDto.getProblemId());
		if(solves.size() > 0){
			return;
		}
		Solved solved = solvedConverter.requestDtoToSolved(user, solvedRequestDto);
		solvedRepository.save(solved);
	}

	public void updateSolved(Long solvedId, String memo){
		Solved solved = solvedRepository.findById(solvedId).orElseThrow();
		solved.updateMemo(memo);
		solvedRepository.save(solved);
	}
}

