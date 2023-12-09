package com.example.baekjoon_recommendation_server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.baekjoon_recommendation_server.domain.User;
import com.example.baekjoon_recommendation_server.repository.UserRepository;

@SpringBootTest
class BaekjoonRecommendationServerApplicationTests {
	UserRepository userRepository;
	@Test
	void addUser() {
		User user = User.builder().build();
		userRepository.save(user);
	}

}
