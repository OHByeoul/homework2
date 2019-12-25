package com.ccmedia.homework.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccmedia.homework.dao.BoardDAOImpl;
import com.ccmedia.homework.model.PagingDTO;
import com.ccmedia.homework.model.ResponseContainer;
import com.ccmedia.homework.util.Constants;

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
			response.setPayload(boardDAO.getBoardList(params));
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

}
