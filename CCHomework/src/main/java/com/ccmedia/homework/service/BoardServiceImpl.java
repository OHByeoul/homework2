package com.ccmedia.homework.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccmedia.homework.dao.BoardDAOImpl;
import com.ccmedia.homework.model.BoardDTO;
import com.ccmedia.homework.model.PagingDTO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDAOImpl boardDAO;
	
	@Autowired
	PagingDTO pagingDTO;
	
	public List<BoardDTO> getBoardList() {
		Map params = new HashMap();
		int listSize = Integer.parseInt(pagingDTO.getListSize());
		int curPageNum = Integer.parseInt(pagingDTO.getCurPageNum());
		int startNum = (curPageNum-1) * listSize+1;
		int endNum = startNum * listSize;
		
		params.put("startNum", startNum);
		params.put("endNum", endNum);
		return boardDAO.getBoardList(params);
	}

	public String getTotalCnt() {
		return boardDAO.getTotalCnt();
	}
}
