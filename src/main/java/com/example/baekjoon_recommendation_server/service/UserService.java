package com.example.baekjoon_recommendation_server.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.baekjoon_recommendation_server.domain.User;
import com.example.baekjoon_recommendation_server.exception.CustomExceptions;
import com.example.baekjoon_recommendation_server.repository.UserRepository;
import com.example.baekjoon_recommendation_server.web.dto.UserRequestDto;
import com.example.baekjoon_recommendation_server.web.dto.UserResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserResponseDto.LoginResDto login(UserRequestDto.LoginDto loginDto){
		String userId = loginDto.getUserId();
		String password = loginDto.getPassword();

		if(!userRepository.existsByUserId(userId)){
			throw new CustomExceptions.LoginException("해당하는 아이디가 없습니다");
		}
		User user = userRepository.findByUserId(userId);
		if(!passwordEncoder.matches(password, user.getPassword())){
			throw new CustomExceptions.LoginException("패스워드가 일치하지 않습니다");
		}
		UserResponseDto.LoginResDto res = UserResponseDto.LoginResDto.builder()
			.userId(userId)
			.password(password)
			.name(user.getUserName())
			.build();
		return res;
	}

	public UserResponseDto.SignUpResDto signUp(UserRequestDto.SignUpDto signUpDto){
		String userId = signUpDto.getUserId();
		String pwd = signUpDto.getPassword();
		String password = passwordEncoder.encode(pwd);
		String name = signUpDto.getName();

		if(userRepository.existsByUserId(userId)){
			throw new CustomExceptions.RegisterException("이미 존재하는 아이디 입니다");
		}
		User user = User.builder()
			.userId(userId)
			.password(password)
			.userName(name)
			.build();
		userRepository.save(user);

		UserResponseDto.SignUpResDto res = UserResponseDto.SignUpResDto.builder()
			.userId(userId)
			.password(password)
			.name(name)
			.build();
		return res;
	}

	public UserResponseDto.ValidateResDto getCurrentUser(UserRequestDto.ValidateDto validateDto){
		String userId = validateDto.getUserId();
		String password= validateDto.getPassword();

		if(!userRepository.existsByUserId(userId)){
			throw new CustomExceptions.ValidateException("유효하지 않은 요청입니다");
		}

		User user = userRepository.findByUserId(userId);
		if(!passwordEncoder.matches(password, user.getPassword())){
			throw new CustomExceptions.ValidateException("유효하지 않은 요청입니다");
		}
		UserResponseDto.ValidateResDto res = UserResponseDto.ValidateResDto.builder()
			.userId(userId)
			.password(password)
			.name(user.getUserName())
			.build();
		return res;
	}
}
