package com.ccmedia.homework.service;

import java.util.List;
import java.util.Map;

import com.ccmedia.homework.model.ResponseContainer;

public interface BoardService {
	public String getBoardList(Map map, ResponseContainer<List> response);
	
	public int getTotalCnt();
}
