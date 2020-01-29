package com.ccmedia.homework.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccmedia.homework.dao.NoticeDAOImpl;
import com.ccmedia.homework.model.NoticeDTO;
import com.ccmedia.homework.model.PagingDTO;
import com.ccmedia.homework.model.ResponseContainer;
import com.ccmedia.homework.util.Constants;
import com.ccmedia.homework.util.Util;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	NoticeDAOImpl noticeDAO;

	@Autowired
	PagingDTO pagingDTO;

	@Override
	public String createNoticeContent(NoticeDTO noticeDTO, ResponseContainer<NoticeDTO> response) {
		try {

			int result = noticeDAO.createNoticeContent(noticeDTO);

			if (result == 1) {
				int noticeId = noticeDTO.getId();
				response.setPayload(noticeDTO);
			}
		} catch (Exception e) {
			response.setErrorMessage("1003");
			e.printStackTrace();
		}
		return response.getResponseToJson();
	}

	public String getNoticeDetailContent(Map<String, String> params, ResponseContainer<NoticeDTO> response) {
		try {
			int noticeId = Integer.parseInt(params.get("noticeId").toString());
			NoticeDTO notice = noticeDAO.getNoticeDetailContent(noticeId);
			//Util.setDateFormat(notice);
			response.setPayload(notice);
		} catch (Exception e) {
			response.setErrorMessage("1002");
			e.printStackTrace();
		}
		return response.getResponseToJson();
	}

	public String updateNoticeContent(NoticeDTO noticeDTO, ResponseContainer<NoticeDTO> response) {
		try {
			int id = noticeDAO.getCurrentNoticeId();
			noticeDTO.setId(id);
			int result = noticeDAO.updateNoticeContent(noticeDTO);

			if (result == 1) {
				response.setPayload(noticeDTO);
			}
		} catch (Exception e) {
			response.setErrorMessage("1004");
			e.printStackTrace();
		}
		return response.getResponseToJson();	
	}

	public String getNoticeList(Map params, ResponseContainer<List> response) {
		try {
			int listSize = Constants.listSize;
			int curPageNum = Integer.parseInt(params.get("curPageNum").toString());
			int totalCnt = noticeDAO.getTotalCnt();
			int startNum = (curPageNum - 1) * listSize + 1;
			int endNum = curPageNum * listSize;

			setBoardPagingValue(totalCnt, curPageNum, listSize);
			response.setPagingDTO(pagingDTO);
			params.put("startNum", startNum);
			params.put("endNum", endNum);
			List<NoticeDTO> notices = noticeDAO.getNoticeList(params);
			System.out.println("len "+notices);
			Util.setNoticeFormatList(notices);
			response.setPayload(notices);
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

	public String deleteContent(Map<String, String> params, ResponseContainer<String> response) {
		try {
			int boardId = Integer.parseInt(params.get("noticeId").toString());
			response.setPayload(String.valueOf(noticeDAO.deleteContent(boardId)));
		} catch (Exception e) {
			response.setErrorMessage("1005");
			e.printStackTrace();
		}
		return response.getResponseToJson();
	}

}
