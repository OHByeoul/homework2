package com.ccmedia.homework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccmedia.homework.dao.BoardDAOImpl;
import com.ccmedia.homework.model.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDAOImpl boardDAO;
	
	public List<BoardDTO> getBoardList() {
		return boardDAO.getBoardList();
	}

	public String getTotalCnt() {
		return boardDAO.getTotalCnt();
	}
}
