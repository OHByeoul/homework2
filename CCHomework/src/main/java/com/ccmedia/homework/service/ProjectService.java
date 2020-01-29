package com.ccmedia.homework.service;

import com.ccmedia.homework.model.ProjectDTO;
import com.ccmedia.homework.model.ResponseContainer;

public interface ProjectService {
	//	public String getBoardList(Map map, ResponseContainer<List> response);
		
//		public int getTotalCnt();
//		
//		public String getDetailContent(Map<String, String> params, ResponseContainer<BoardDTO> response);
		
		public String createProjectContent(ProjectDTO noticeDTO, ResponseContainer<ProjectDTO> response);
		
//		public String updateContent(BoardDTO boardDTO, ResponseContainer<BoardDTO> response);
		
//		public String deleteContent(Map<String, String> params, ResponseContainer<String> response);
		
//		public List<BoardDTO> getSearchedList(String searchName, ResponseContainer<List> response);
}
