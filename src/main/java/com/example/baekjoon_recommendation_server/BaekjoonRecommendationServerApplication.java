package com.example.baekjoon_recommendation_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BaekjoonRecommendationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaekjoonRecommendationServerApplication.class, args);
	}

}
