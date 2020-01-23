package com.ccmedia.homework.service;

import com.ccmedia.homework.model.NoticeDTO;
import com.ccmedia.homework.model.ResponseContainer;

public interface NoticeService {
	//	public String getBoardList(Map map, ResponseContainer<List> response);
		
//		public int getTotalCnt();
//		
//		public String getDetailContent(Map<String, String> params, ResponseContainer<BoardDTO> response);
		
		public String createNoticeContent(NoticeDTO noticeDTO, ResponseContainer<NoticeDTO> response);
		
//		public String updateContent(BoardDTO boardDTO, ResponseContainer<BoardDTO> response);
		
//		public String deleteContent(Map<String, String> params, ResponseContainer<String> response);
		
//		public List<BoardDTO> getSearchedList(String searchName, ResponseContainer<List> response);
}
