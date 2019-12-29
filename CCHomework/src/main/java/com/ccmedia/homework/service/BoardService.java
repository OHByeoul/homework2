package com.ccmedia.homework.service;

import java.util.List;
import java.util.Map;

import com.ccmedia.homework.model.BoardDTO;
import com.ccmedia.homework.model.ResponseContainer;

public interface BoardService {
	public String getBoardList(Map map, ResponseContainer<List> response);
	
	public int getTotalCnt();
	
	public String getDetailContent(Map<String, String> params, ResponseContainer<BoardDTO> response);
	
	public String createContent(BoardDTO boardDTO, ResponseContainer<BoardDTO> response);
	
	public String updateContent(BoardDTO boardDTO, ResponseContainer<BoardDTO> response);
	
	public String deleteContent(Map<String, String> params, ResponseContainer<String> response);
	
	public List<BoardDTO> getSearchedList(String searchName, ResponseContainer<List> response);
}
