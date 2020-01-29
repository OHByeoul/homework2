package com.ccmedia.homework.dao;

import java.util.List;
import java.util.Map;

import com.ccmedia.homework.model.NoticeDTO;
import com.ccmedia.homework.model.ProjectDTO;

public interface ProjectDAO {
//		public List<NoticeDTO> getBoardList(Map params);
//		
//		public int getTotalCnt();
//
//		public NoticeDTO getDetailContent(int boardId);

		public int createProjectContent(ProjectDTO project);

		public ProjectDTO getProjectDetailContent(int noticeId);

		public int updateProjectContent(ProjectDTO project);

		public int getCurrentNoticeId();

		public int getTotalCnt();

		public List<ProjectDTO> getProjectList(Map params);

		int deleteContent(int noticeId);

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
