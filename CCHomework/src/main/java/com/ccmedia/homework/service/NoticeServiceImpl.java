package com.ccmedia.homework.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccmedia.homework.dao.NoticeDAO;
import com.ccmedia.homework.model.BoardDTO;
import com.ccmedia.homework.model.NoticeDTO;
import com.ccmedia.homework.model.PagingDTO;
import com.ccmedia.homework.model.ResponseContainer;
import com.ccmedia.homework.util.Util;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	NoticeDAO noticeDAO;

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
			Util.setDateFormat(notice);
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

}
