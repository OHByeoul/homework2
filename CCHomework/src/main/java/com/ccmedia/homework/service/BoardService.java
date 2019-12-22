package com.ccmedia.homework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccmedia.homework.dao.BoardDAO;
import com.ccmedia.homework.model.Board;

@Service
public class BoardService {
	@Autowired
	BoardDAO boardDAO;
	
	public List<Board> getBoardList() {
		return boardDAO.getBoardList();
	}
}
