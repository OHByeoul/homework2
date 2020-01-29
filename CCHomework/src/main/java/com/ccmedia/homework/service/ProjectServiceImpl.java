package com.ccmedia.homework.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccmedia.homework.dao.NoticeDAOImpl;
import com.ccmedia.homework.dao.ProjectDAOImpl;
import com.ccmedia.homework.model.NoticeDTO;
import com.ccmedia.homework.model.PagingDTO;
import com.ccmedia.homework.model.ProjectDTO;
import com.ccmedia.homework.model.ResponseContainer;
import com.ccmedia.homework.util.Constants;
import com.ccmedia.homework.util.Util;

@Service
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	ProjectDAOImpl projectDAO;

	@Autowired
	PagingDTO pagingDTO;

	@Override
	public String createProjectContent(ProjectDTO projectDTO, ResponseContainer<ProjectDTO> response) {
		try {

			int result = projectDAO.createProjectContent(projectDTO);

			if (result == 1) {
				int projectId = projectDTO.getId();
				response.setPayload(projectDTO);
			}
		} catch (Exception e) {
			response.setErrorMessage("1003");
			e.printStackTrace();
		}
		return response.getResponseToJson();
	}

	public String getProjectDetailContent(Map<String, String> params, ResponseContainer<ProjectDTO> response) {
		try {
			int projectId = Integer.parseInt(params.get("projectId").toString());
			ProjectDTO project = projectDAO.getProjectDetailContent(projectId);
			//Util.setDateFormat(project);
			response.setPayload(project);
		} catch (Exception e) {
			response.setErrorMessage("1002");
			e.printStackTrace();
		}
		return response.getResponseToJson();
	}

	public String updateProjectContent(ProjectDTO projectDTO, ResponseContainer<ProjectDTO> response) {
		try {
			int id = projectDAO.getCurrentNoticeId();
			projectDTO.setId(id);
			int result = projectDAO.updateProjectContent(projectDTO);

			if (result == 1) {
				response.setPayload(projectDTO);
			}
		} catch (Exception e) {
			response.setErrorMessage("1004");
			e.printStackTrace();
		}
		return response.getResponseToJson();	
	}

	public String getProjectList(Map params, ResponseContainer<List> response) {
		try {
			int listSize = Constants.listSize;
			int curPageNum = Integer.parseInt(params.get("curPageNum").toString());
			int totalCnt = projectDAO.getTotalCnt();
			int startNum = (curPageNum - 1) * listSize + 1;
			int endNum = curPageNum * listSize;

			setBoardPagingValue(totalCnt, curPageNum, listSize);
			response.setPagingDTO(pagingDTO);
			params.put("startNum", startNum);
			params.put("endNum", endNum);
			List<ProjectDTO> projects = projectDAO.getProjectList(params);
			Util.setProjectFormatList(projects);
			response.setPayload(projects);
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
			int boardId = Integer.parseInt(params.get("projectId").toString());
			response.setPayload(String.valueOf(projectDAO.deleteContent(boardId)));
		} catch (Exception e) {
			response.setErrorMessage("1005");
			e.printStackTrace();
		}
		return response.getResponseToJson();
	}

}
