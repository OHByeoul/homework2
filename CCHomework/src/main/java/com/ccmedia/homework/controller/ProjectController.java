package com.ccmedia.homework.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccmedia.homework.model.NoticeDTO;
import com.ccmedia.homework.model.PagingDTO;
import com.ccmedia.homework.model.ProjectDTO;
import com.ccmedia.homework.model.ResponseContainer;
import com.ccmedia.homework.service.NoticeServiceImpl;
import com.ccmedia.homework.service.ProjectServiceImpl;

@Controller
@RequestMapping(value = "/project", method = RequestMethod.GET)
public class ProjectController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);
	
	@Autowired
	ProjectServiceImpl projectService;
	
	@Autowired
	PagingDTO pagingDTO;
	
	@GetMapping({"/",""})
	public String initProjectList(Model model) {
		if(pagingDTO.getCurPageNum() > 0) {
			model.addAttribute("curPageNum", pagingDTO.getCurPageNum());
		}
		return "project/project";
	}
	
	@GetMapping(value = "/createProjectContent")
	public String initProjectCreateContent(Model model) {
		return "project/projectCreate";
	}
	
	@PostMapping(value = "/getProjectList", produces = "application/json; charset=utf8")
	@ResponseBody
	public String getBoardList(@RequestBody Map<String, String> params, Model model) {
		ResponseContainer<List> response = new ResponseContainer<List>();
		try {
			String curPageNum = params.get("curPageNum");

			String responseJson = projectService.getProjectList(params, response);

			model.addAttribute("curPageNum", curPageNum);
			return responseJson;
		} catch (Exception e) {
			logger.error("ProjectController /getNoticeList " + e.getMessage());
		}
		return response.getErrorMessage();
	}
	
	@PostMapping(value = "/createProjectContent", produces = "application/json; charset=utf8")
	@ResponseBody
	public String createProjectContent(@ModelAttribute ProjectDTO projectDTO, Model model) {
		ResponseContainer<ProjectDTO> response = new ResponseContainer<ProjectDTO>();
		
		try {
			String responseJson = projectService.createProjectContent(projectDTO,response);
			return responseJson;
		} catch (Exception e) {
			logger.error("ProjectController /createContent " + e.getMessage());
		}
		return response.getErrorMessage();
	}
	
	@GetMapping(value = "/getProjectDetailContent/{projectId}", produces = "application/json; charset=utf8")
	public String initProjectDetailContent(@PathVariable String projectId, Model model) {
		model.addAttribute("projectId",projectId);
		return "project/projectDetail";
	}
	
	@PostMapping(value = "/getProjectDetailContent", produces = "application/json; charset=utf8")
	@ResponseBody
	public String getProjectDetailContent(@RequestBody Map<String, String> params, Model model) {
		ResponseContainer<ProjectDTO> response = new ResponseContainer<ProjectDTO>();
		try {
			String responseJson = projectService.getProjectDetailContent(params,response);
			return responseJson;
		} catch (Exception e) {
			logger.error("ProjectController /getProjectDetailContent " + e.getMessage());
		}
		return response.getErrorMessage();
	}
	
	@GetMapping(value = "/updateProjectContent/{projectId}")
	public String initUpdateContent(@PathVariable String projectId, Model model) {
		model.addAttribute("projectId", projectId);
		return "project/projectUpdate";
	}
	
	@PostMapping(value = "/updateProjectContent", produces = "application/json; charset=utf8")
	@ResponseBody
	public String modifyContent(@ModelAttribute ProjectDTO projectDTO, Model model) {
		ResponseContainer<ProjectDTO> response = new ResponseContainer<ProjectDTO>();
		
		try {
			String responseJson = projectService.updateProjectContent(projectDTO,response);
			return responseJson;
		} catch (Exception e) {
			logger.error("ProjectController /modifyContent " + e.getMessage());
		}
		return response.getErrorMessage();
	}
	
	@PostMapping(value = "/deleteContent", produces = "application/json; charset=utf8")
	@ResponseBody
	public String deleteContent(@RequestBody Map<String, String> params, Model model) {
		ResponseContainer<String> response = new ResponseContainer<String>();
		try {
			String responseJson = projectService.deleteContent(params, response);
			return responseJson;
		} catch (Exception e) {
			logger.error("ProjectController /deleteContent " + e.getMessage());
		}
		return response.getErrorMessage();
	}
}
