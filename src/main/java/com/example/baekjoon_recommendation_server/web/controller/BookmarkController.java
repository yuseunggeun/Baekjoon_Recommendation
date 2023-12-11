package com.example.baekjoon_recommendation_server.web.controller;

import java.awt.print.Book;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.baekjoon_recommendation_server.domain.User;
import com.example.baekjoon_recommendation_server.exception.ResponseMessage;
import com.example.baekjoon_recommendation_server.exception.StatusCode;
import com.example.baekjoon_recommendation_server.repository.BookmarkRepository;
import com.example.baekjoon_recommendation_server.repository.UserRepository;
import com.example.baekjoon_recommendation_server.service.BookmarkService;
import com.example.baekjoon_recommendation_server.service.ProblemService;
import com.example.baekjoon_recommendation_server.service.UserService;
import com.example.baekjoon_recommendation_server.web.dto.BookmarkDto;
import com.example.baekjoon_recommendation_server.web.dto.BookmarkRequestDto;
import com.example.baekjoon_recommendation_server.web.dto.MemoDto;
import com.example.baekjoon_recommendation_server.web.dto.ProblemDetailDto;
import com.example.baekjoon_recommendation_server.web.dto.SearchDto;
import com.example.baekjoon_recommendation_server.web.dto.UserRequestDto;
import com.example.baekjoon_recommendation_server.web.dto.UserResponseDto;
import com.example.baekjoon_recommendation_server.web.dto.base.DefaultRes;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController {
	private final BookmarkRepository bookmarkRepository;
	private final UserRepository userRepository;
	private final BookmarkService bookmarkService;
	private final ProblemService problemService;
	private final UserService userService;

	@GetMapping("/bookmarks")
	public ResponseEntity bookmarks(@RequestHeader("userId") String userId, @RequestHeader("password") String password){
		try{
			UserRequestDto.ValidateDto req = UserRequestDto.ValidateDto.builder()
				.userId(userId)
				.password(password)
				.build();
			UserResponseDto.ValidateResDto user = userService.getCurrentUser(req);
			List<BookmarkDto> bookmarkDtoList = bookmarkService.getBookmarks(user.getUserId());

			return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.BOOKMARK_READ_SUCCESS, bookmarkDtoList), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/{bookmarkId}")
	public ResponseEntity bookmarkDetail(@PathVariable Long bookmarkId){
		try{
			BookmarkDto bookmarkDto = bookmarkService.getBookmark(bookmarkId);
			return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.BOOKMARK_READ_SUCCESS, bookmarkDto), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping("/{problemId}")
	public ResponseEntity addBookmark(@PathVariable Long problemId, @RequestHeader("userId") String userId, @RequestHeader("password") String password,
		@RequestBody BookmarkRequestDto request){
		try{
			UserRequestDto.ValidateDto req = UserRequestDto.ValidateDto.builder()
				.userId(userId)
				.password(password)
				.build();
			UserResponseDto.ValidateResDto user = userService.getCurrentUser(req);

			//ProblemDetailDto problemDetailDto = problemService.getProblemDetail(problemId);
			bookmarkService.addBookmark(user.getUserId(), request);

			return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.BOOKMARK_ADD_SUCCESS), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{bookmarkId}")
	public ResponseEntity deleteBookmark(@PathVariable Long bookmarkId, @RequestHeader("userId") String userId, @RequestHeader("password") String password){
		try{
			UserRequestDto.ValidateDto req = UserRequestDto.ValidateDto.builder()
				.userId(userId)
				.password(password)
				.build();
			UserResponseDto.ValidateResDto user = userService.getCurrentUser(req);

			bookmarkService.deleteBookmark(bookmarkId);
			return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.BOOKMARK_DELETE_SUCCESS), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{bookmarkId}")
	public ResponseEntity updateBookmarkMemo(@PathVariable Long bookmarkId, @RequestBody MemoDto request, @RequestHeader("userId") String userId, @RequestHeader("password") String password){
		try{
			UserRequestDto.ValidateDto req = UserRequestDto.ValidateDto.builder()
				.userId(userId)
				.password(password)
				.build();
			UserResponseDto.ValidateResDto user = userService.getCurrentUser(req);

			String memo = request.getMemo();;
			bookmarkService.updateBookmark(bookmarkId, memo);
			return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.BOOKMARK_UPDATE_SUCCESS), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
		}
	}
}
