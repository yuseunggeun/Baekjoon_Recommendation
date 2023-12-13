package com.example.baekjoon_recommendation_server.web.controller;


import java.net.URI;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.baekjoon_recommendation_server.converter.SearchConverter;
import com.example.baekjoon_recommendation_server.exception.ResponseMessage;
import com.example.baekjoon_recommendation_server.exception.StatusCode;
import com.example.baekjoon_recommendation_server.service.ProblemService;
import com.example.baekjoon_recommendation_server.web.dto.ProblemDetailDto;
import com.example.baekjoon_recommendation_server.web.dto.ProblemDto;
import com.example.baekjoon_recommendation_server.web.dto.SearchDto;
import com.example.baekjoon_recommendation_server.web.dto.base.DefaultRes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/problem")
public class ProblemController {
	@Autowired
	private RestTemplate restTemplate;
	private final ProblemService problemService;
	private final SearchConverter searchConverter;
	@GetMapping("/test")
	public String test(){
		return "test";
	}

	@PostMapping("/search")
	public ResponseEntity search(@RequestBody SearchDto request){
		try{
			String url = problemService.getSearchQuery(request);

			URI uri = UriComponentsBuilder
				.fromUriString("https://solved.ac/api/v3/search/problem")
				.queryParam("query", url)
				.encode().build().toUri();

			log.info("search uri : ", uri);

			ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(response.getBody());
			List<ProblemDto> problemDtoList = searchConverter.jsonToList(jsonObject);

			log.info("search success");
			return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.PROBLEM_SEARCH_SUCCESS, problemDtoList), HttpStatus.OK);
		} catch (Exception e) {
			log.error("error : ", e);
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}") //나중에 body로 난이도도 보내기
	public ResponseEntity detail(@PathVariable Long id){
		try{
			log.info("get problem detail : ", id);
			ProblemDetailDto problemDetailDto = problemService.getProblemDetail(id);

			return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.PROBLEM_DETAIL_SUCCESS, problemDetailDto), HttpStatus.OK);
		} catch (Exception e){
			log.error("error : ", e);
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}
}
