package com.example.baekjoon_recommendation_server.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.baekjoon_recommendation_server.web.dto.SearchDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SearchConverter {
	//public Map<Integer, String> diff = new HashMap<Integer, String>();
	public String toSearchQuery(SearchDto searchDto){
		String solveCount = "s" + "#" + searchDto.getMinSolveCount().toString() + ".." + searchDto.getMaxSolveCount().toString();
		String difficulty = "*" + diffToSting(searchDto.getMinDifficulty()) + ".." + diffToSting(searchDto.getMaxDifficulty());
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

	public String diffToSting(Integer diff){
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
}
