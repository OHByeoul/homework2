package com.ccmedia.homework.dao;

import java.util.List;
import java.util.Map;

import com.ccmedia.homework.model.NoticeDTO;

public interface NoticeDAO {
//		public List<NoticeDTO> getBoardList(Map params);
//		
//		public int getTotalCnt();
//
//		public NoticeDTO getDetailContent(int boardId);

		public int createNoticeContent(NoticeDTO notice);

		public NoticeDTO getNoticeDetailContent(int noticeId);

		public int updateNoticeContent(NoticeDTO noticeDTO);

		public int getCurrentNoticeId();

//		public int updateContent(NoticeDTO board);
//
//		public int getCurrentId();
//
//		public int deleteContent(int boardId);
//
//		public int setViews(int boardId);
//		
//		public List<NoticeDTO> getSearchedList(String searchName);

}
