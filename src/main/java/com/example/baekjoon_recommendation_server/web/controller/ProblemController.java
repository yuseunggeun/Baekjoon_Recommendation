package com.example.baekjoon_recommendation_server.web.controller;


import java.net.URI;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.baekjoon_recommendation_server.repository.ProblemRepository;
import com.example.baekjoon_recommendation_server.service.ProblemService;
import com.example.baekjoon_recommendation_server.web.dto.SearchDto;
import com.example.baekjoon_recommendation_server.web.dto.base.DefaultRes;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/problem")
public class ProblemController {
	@Autowired
	private RestTemplate restTemplate;
	private final ProblemService problemService;
	private final ProblemRepository problemRepository;

	@GetMapping("/test")
	public String test(){
		return "test";
	}

	@GetMapping("/search")
	public ResponseEntity search(@RequestBody SearchDto request){
		try{
			String url = problemService.getSearchQuery(request);

			//System.out.println(url);

			URI uri = UriComponentsBuilder
				.fromUriString("https://solved.ac/api/v3/search/problem")
				.queryParam("query", url)
				.encode().build().toUri();
			//System.out.println(uri);

			ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(response.getBody());
			return new ResponseEntity(DefaultRes.res(200, "search_success", jsonObject), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}
}
