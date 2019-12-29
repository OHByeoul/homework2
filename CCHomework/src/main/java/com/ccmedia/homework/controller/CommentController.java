package com.ccmedia.homework.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccmedia.homework.model.CommentDTO;
import com.ccmedia.homework.model.ResponseContainer;
import com.ccmedia.homework.service.CommentServiceImpl;

@Controller
@RequestMapping(value = "/comment", method = RequestMethod.GET)
public class CommentController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	CommentServiceImpl commentService;
	
	@PostMapping(value="/getComments", produces = "application/json; charset=utf8")
	@ResponseBody
	public String logout(@RequestBody String boardId) {
		ResponseContainer<List> response = new ResponseContainer<List>();
		try {
			String responseJson = commentService.getComments(boardId, response);
			return responseJson;
		} catch (Exception e) {
			logger.error("CommentController /getComments " + e.getMessage());
		}
		return response.getErrorMessage();
	}
	
	@PostMapping(value="/createComment", produces = "application/json; charset=utf8")
	@ResponseBody
	public String createComment(@RequestBody Map<String,String> params) {
		ResponseContainer<List> response = new ResponseContainer<List>();
		try {
			CommentDTO commentDTO = new CommentDTO();
			commentDTO.setBoardId(Integer.parseInt(params.get("boardId").toString()));
			commentDTO.setContent(params.get("content").toString());
			commentDTO.setCreatedBy(params.get("createdBy").toString());
			String responseJson = commentService.createComment(commentDTO, response);
			return responseJson;
		} catch (Exception e) {
			logger.error("CommentController /createComment " + e.getMessage());
		}
		return response.getErrorMessage();
	}
}
