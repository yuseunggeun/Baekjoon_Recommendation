package com.example.baekjoon_recommendation_server.service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.baekjoon_recommendation_server.converter.BookmarkConverter;
import com.example.baekjoon_recommendation_server.domain.Bookmark;
import com.example.baekjoon_recommendation_server.domain.User;
import com.example.baekjoon_recommendation_server.exception.CustomExceptions;
import com.example.baekjoon_recommendation_server.repository.BookmarkRepository;
import com.example.baekjoon_recommendation_server.repository.UserRepository;
import com.example.baekjoon_recommendation_server.web.dto.BookmarkDto;
import com.example.baekjoon_recommendation_server.web.dto.BookmarkRequestDto;
import com.example.baekjoon_recommendation_server.web.dto.ProblemDetailDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
	private final UserRepository userRepository;
	private final BookmarkRepository bookmarkRepository;
	private final BookmarkConverter bookmarkConverter;

	public List<BookmarkDto> getBookmarks(String userId){
		User user = userRepository.findByUserId(userId);

		List<Bookmark> bookmarks = user.getBookmarks();
		List<BookmarkDto> bookmarkDtos = new ArrayList<BookmarkDto>();
		for(Bookmark bookmark : bookmarks){
			bookmarkDtos.add(bookmarkConverter.toBookmarkDto(
				bookmark.getId(),
				bookmark.getProblemId(),
				bookmark.getTitle(),
				bookmark.getDifficulty(),
				bookmark.getSolveCount(),
				bookmark.getMemo()
			));
		}
		return bookmarkDtos;
	}

	public BookmarkDto getBookmark(Long bookmarkId){
		Bookmark bookmark = bookmarkRepository.findById(bookmarkId).orElseThrow();
		return bookmarkConverter.toBookmarkDto(
			bookmark.getId(),
			bookmark.getProblemId(),
			bookmark.getTitle(),
			bookmark.getDifficulty(),
			bookmark.getSolveCount(),
			bookmark.getMemo()
		);
	}

	public void addBookmark(String userId, BookmarkRequestDto bookmarkRequestDto){ //난이도 이슈
		User user = userRepository.findByUserId(userId);

		List<Bookmark> bookmarks = bookmarkRepository.findByUserAndProblemId(user, bookmarkRequestDto.getProblemId());
		if(bookmarks.size() > 0){
			throw new CustomExceptions.BookmarkException("이미 북마크한 문제입니다");
		}

		Bookmark bookmark = bookmarkConverter.requestDtoToBookmark(user, bookmarkRequestDto);
		bookmarkRepository.save(bookmark);
	}

	public void deleteBookmark(Long bookmarkId){
		Bookmark bookmark = bookmarkRepository.findById(bookmarkId).orElseThrow();
		bookmarkRepository.delete(bookmark);
	}

	public void updateBookmark(Long bookmarkId, String memo){
		Bookmark bookmark = bookmarkRepository.findById(bookmarkId).orElseThrow();
		bookmark.updateMemo(memo);
		bookmarkRepository.save(bookmark);
	}
}
