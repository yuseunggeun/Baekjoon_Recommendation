package com.example.baekjoon_recommendation_server.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.PagesPerMinute;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.example.baekjoon_recommendation_server.converter.ProblemDetailConverter;
import com.example.baekjoon_recommendation_server.converter.SearchConverter;
import com.example.baekjoon_recommendation_server.repository.ProblemRepository;
import com.example.baekjoon_recommendation_server.web.dto.ProblemDetailDto;
import com.example.baekjoon_recommendation_server.web.dto.SearchDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProblemService {
	private final ProblemRepository problemRepository;
	private final SearchConverter searchConverter;
	private final ProblemDetailConverter problemDetailConverter;

	public String getSearchQuery(SearchDto searchDto){
		return searchConverter.toSearchQuery(searchDto);
	}

	public ProblemDetailDto getProblemDetail(Long id) throws IOException {
		String url = "https://www.acmicpc.net/problem/" + id.toString();
		Document document = Jsoup.connect(url).get();

		String title = document.select("span#problem_title").get(0).text();
		Integer solveCount = Integer.parseInt(document.select("table#problem-info > tbody > tr > td").get(4).text());

		Elements contents = document.select("div#problem_description > p");
		String content = "";
		for(Element elem : contents){
			content += elem.text();
			content += "\n";
		}

		Elements inputRules = document.select("div#problem_input > p");
		String inputRule = "";
		for(Element elem : inputRules){
			inputRule += elem.text();
			inputRule += "\n";
		}

		Elements outputRules = document.select("div#problem_output > p");
		String outputRule = "";
		for(Element elem : inputRules){
			outputRule += elem.text();
			outputRule += "\n";
		}

		Elements samples = document.select("pre.sampledata");
		List<String> sample = new ArrayList<String>();
		for(Element elem : samples){
			sample.add(elem.text());
		}

		return problemDetailConverter.toProblemDetail(id, title, solveCount, content, inputRule, outputRule,
			sample, false, false);
	}
}
