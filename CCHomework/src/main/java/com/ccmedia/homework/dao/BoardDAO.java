package com.ccmedia.homework.dao;

import java.util.List;
import java.util.Map;

import com.ccmedia.homework.model.BoardDTO;

public interface BoardDAO {
	public List<BoardDTO> getBoardList(Map params);
	
	public int getTotalCnt();
}
