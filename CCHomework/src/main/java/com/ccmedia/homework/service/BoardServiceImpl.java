package com.ccmedia.homework.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccmedia.homework.dao.BoardDAOImpl;
import com.ccmedia.homework.model.BoardDTO;
import com.ccmedia.homework.model.NoticeDTO;
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
			int endNum = curPageNum * listSize;

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
		return response.getResponseToJson();
	}

	private void setBoardPagingValue(int totalCnt, int curPageNum, int listSize) {
		pagingDTO.setTotalCnt(totalCnt);
		pagingDTO.setCurPageNum(curPageNum);
		pagingDTO.setListSize(listSize);
	}

	@Override
	public int getTotalCnt() {
		return boardDAO.getTotalCnt();
	}

	@Override
	public String getDetailContent(Map<String, String> params, ResponseContainer<BoardDTO> response) {
		try {
			int boardId = Integer.parseInt(params.get("boardId").toString());
			BoardDTO board = boardDAO.getDetailContent(boardId);
			boardDAO.setViews(boardId);
			Util.setDateFormat(board);
			response.setPayload(board);
		} catch (Exception e) {
			response.setErrorMessage("1002");
			e.printStackTrace();
		}
		return response.getResponseToJson();
	}

	@Override
	public String createContent(BoardDTO boardDTO, ResponseContainer<BoardDTO> response) {
		try {

			int result = boardDAO.createContent(boardDTO);

			if (result == 1) {
				int boardId = boardDTO.getId();
				response.setPayload(boardDTO);
			}
		} catch (Exception e) {
			response.setErrorMessage("1003");
			e.printStackTrace();
		}
		return response.getResponseToJson();
	}
	




	@Override
	public String updateContent(BoardDTO boardDTO, ResponseContainer<BoardDTO> response) {
		try {
			int id = boardDAO.getCurrentId();
			boardDTO.setId(id);
			int result = boardDAO.updateContent(boardDTO);

			if (result == 1) {
				response.setPayload(boardDTO);
			}
		} catch (Exception e) {
			response.setErrorMessage("1004");
			e.printStackTrace();
		}
		return response.getResponseToJson();
	}

	@Override
	public String deleteContent(Map<String, String> params, ResponseContainer<String> response) {
		try {
			int boardId = Integer.parseInt(params.get("boardId").toString());
			boardDAO.deleteComments(boardId);
			response.setPayload(String.valueOf(boardDAO.deleteContent(boardId)));
		} catch (Exception e) {
			response.setErrorMessage("1005");
			e.printStackTrace();
		}
		return response.getResponseToJson();
	}

	@Override
	public List<BoardDTO> getSearchedList(String searchName, ResponseContainer<List> response) {
		List<BoardDTO> boards = null;
		try {
			boards = boardDAO.getSearchedList(searchName);
			Util.setDateFormatList(boards);
		} catch (Exception e) {
			response.setErrorMessage("1006");
			e.printStackTrace();
		}
		return boards;
		
	}

}