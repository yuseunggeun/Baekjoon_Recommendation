package com.example.baekjoon_recommendation_server.converter;

import java.util.ArrayList;
import java.util.List;

//import org.json.JSONArray;
//import org.json.JSONObject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.example.baekjoon_recommendation_server.web.dto.ProblemDto;
import com.example.baekjoon_recommendation_server.web.dto.SearchDto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SearchConverter {

	private final ProblemConverter problemConverter;
	public String toSearchQuery(SearchDto searchDto){
		String solveCount = "s" + "#" + searchDto.getMinSolveCount().toString() + ".." + searchDto.getMaxSolveCount().toString();
		String difficulty = "*" + diffToString(searchDto.getMinDifficulty()) + ".." + diffToString(searchDto.getMaxDifficulty());
		String op = searchDto.getLogical().equals("and") ? "&" : "|";

		String query = solveCount + "&" + difficulty + "&";

		List<String> tagList = searchDto.getTags();
		query += "(";
		String tag;
		for(int i = 0; i < tagList.size(); i++){
			tag = tagList.get(i);
			query += "#";
			query += tag;
			if(i != tagList.size()-1){ query += op; }
		}
		query += ")";
		return query;
	}

	public String diffToString(Integer diff){
		String string;
		if(diff <= 0){ return "0";}
		else if(diff <= 5){ string = "b"; }
		else if(diff <= 10){ string = "s"; }
		else if(diff <= 15){ string = "g"; }
		else if(diff <= 20){ string = "p"; }
		else if(diff <= 25){ string = "d"; }
		else { string = "r"; }

		if(diff%5 == 0){string += "1";}
		else{
			Integer tmp = 6 - diff%5;
			string += tmp.toString();
		}
		return string;
	}

	public List<ProblemDto> jsonToList(JSONObject data){
		List<ProblemDto> problems = new ArrayList<>();
		Long problemId;
		String title;
		Integer difficulty;

		JSONArray items = (JSONArray)data.get("items");
		JSONObject problem = new JSONObject();
		JSONObject tagInfo = new JSONObject();
		for(int i = 0; i < items.size(); i++){
			problem = (JSONObject)items.get(i);

			problemId = (Long)problem.get("problemId");
			title = (String)problem.get("titleKo");
			difficulty = ((Long)problem.get("level")).intValue();
			//tagList.clear();
			List<String> tagList = new ArrayList<>();

			JSONArray tags = (JSONArray)problem.get("tags");
			for(int j = 0; j < tags.size(); j++){
				tagInfo = (JSONObject)tags.get(j);
				tagList.add((String)tagInfo.get("key"));
			}
			problems.add(problemConverter.toProblemDto(problemId, title, difficulty, tagList));
		}
		return problems;
	}
}
