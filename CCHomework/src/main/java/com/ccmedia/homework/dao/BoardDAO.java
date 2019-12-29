package com.ccmedia.homework.dao;

import java.util.List;
import java.util.Map;

import com.ccmedia.homework.model.BoardDTO;

public interface BoardDAO {
	public List<BoardDTO> getBoardList(Map params);
	
	public int getTotalCnt();

	public BoardDTO getDetailContent(int boardId);

	public int createContent(BoardDTO board);

	public int updateContent(BoardDTO board);

	public int getCurrentId();

	public int deleteContent(int boardId);

	public int setViews(int boardId);
	
	public List<BoardDTO> getSearchedList(String searchName);
}
