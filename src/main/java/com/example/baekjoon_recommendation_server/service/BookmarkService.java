package com.example.baekjoon_recommendation_server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.baekjoon_recommendation_server.converter.BookmarkConverter;
import com.example.baekjoon_recommendation_server.domain.Bookmark;
import com.example.baekjoon_recommendation_server.domain.User;
import com.example.baekjoon_recommendation_server.repository.BookmarkRepository;
import com.example.baekjoon_recommendation_server.repository.UserRepository;
import com.example.baekjoon_recommendation_server.web.dto.BookmarkDto;
import com.example.baekjoon_recommendation_server.web.dto.ProblemDetailDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookmarkService {
	private final UserRepository userRepository;
	private final BookmarkRepository bookmarkRepository;
	private final BookmarkConverter bookmarkConverter;

	public List<BookmarkDto> getBookmarks(Long id){
		User user = userRepository.findById(id).orElseThrow();
		List<Bookmark> bookmarks = user.getBookmarks();
		List<BookmarkDto> bookmarkDtos = new ArrayList<BookmarkDto>();
		for(Bookmark bookmark : bookmarks){
			bookmarkDtos.add(bookmarkConverter.toBookmarkDto(
				bookmark.getId(),
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
			bookmark.getTitle(),
			bookmark.getDifficulty(),
			bookmark.getSolveCount(),
			bookmark.getMemo()
		);
	}

	public void addBookmark(Long userId, ProblemDetailDto problemDetailDto){
		User user = userRepository.findById(1l).orElseThrow();
		Bookmark bookmark = bookmarkConverter.problemToBookmarkDto(user, problemDetailDto);
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
