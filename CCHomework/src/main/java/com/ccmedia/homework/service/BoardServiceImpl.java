package com.ccmedia.homework.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccmedia.homework.dao.BoardDAOImpl;
import com.ccmedia.homework.model.BoardDTO;
import com.ccmedia.homework.model.PagingDTO;
import com.ccmedia.homework.model.ResponseContainer;
import com.ccmedia.homework.util.Constants;
import com.ccmedia.homework.util.Util;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAOImpl boardDAO;

	@Autowired
	PagingDTO pagingDTO;

	@Override
	public String getBoardList(Map params, ResponseContainer<List> response) {
		try {
			int listSize = Constants.listSize;
			int curPageNum = Integer.parseInt(params.get("curPageNum").toString());
			int totalCnt = boardDAO.getTotalCnt();
			int startNum = (curPageNum - 1) * listSize + 1;
			int endNum = startNum * listSize;

			setBoardPagingValue(totalCnt, curPageNum, listSize);
			response.setPagingDTO(pagingDTO);
			params.put("startNum", startNum);
			params.put("endNum", endNum);
			List<BoardDTO> boards = boardDAO.getBoardList(params);
			Util.setDateFormatList(boards);
			response.setPayload(boards);
		} catch (Exception e) {
			response.setErrorMessage("1001");
			e.printStackTrace();
		}
		System.out.println("response json " + response.getResponseToJson());
		return response.getResponseToJson();
	}

	private void setBoardPagingValue(int totalCnt, int curPageNum, int listSize) {
		pagingDTO.setTotalCnt(totalCnt);
		pagingDTO.setCurPageNum(curPageNum);
		pagingDTO.setListSize(listSize);
	}

	public int getTotalCnt() {
		return boardDAO.getTotalCnt();
	}

	public String getDetailContent(Map<String, String> params, ResponseContainer<BoardDTO> response) {
		try {
			int boardId = Integer.parseInt(params.get("boardId").toString());
			BoardDTO board = boardDAO.getDetailContent(boardId);
			Util.setDateFormat(board);
			response.setPayload(board);
		}catch (Exception e) {
			response.setErrorMessage("1002");
			e.printStackTrace();
		}
		return response.getResponseToJson();
	}

	public String createContent(BoardDTO boardDTO, ResponseContainer<BoardDTO> response) {
		try {
			int result = boardDAO.createContent(boardDTO);
			if(result == 1) {
				
			}
		} catch (Exception e) {
			response.setErrorMessage("1003");
			e.printStackTrace();
		}
		return null;
	}

}
