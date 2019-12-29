package com.ccmedia.homework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccmedia.homework.dao.CommentDAOImpl;
import com.ccmedia.homework.model.CommentDTO;
import com.ccmedia.homework.model.ResponseContainer;
import com.ccmedia.homework.util.Util;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentDAOImpl commentDAO;
	
	@Override
	public String getComments(String boardId,  ResponseContainer<List> response) {
		try {
			boardId = boardId.replaceAll("\"", "");
			List<CommentDTO> comments = commentDAO.getComments(boardId);
			Util.setCommentDateFormatList(comments);
			response.setPayload(comments);
		} catch (Exception e) {
			response.setErrorMessage("1007");
			e.printStackTrace(); 
		}
		return response.getResponseToJson();
	}

	@Override
	public String createComment(CommentDTO commentDTO, ResponseContainer<List> response) {
		try {
			int result = commentDAO.createComment(commentDTO);
			if(result == 1) {
				List<CommentDTO> comments = commentDAO.getComments(String.valueOf(commentDTO.getBoardId()));
				Util.setCommentDateFormatList(comments);
				response.setPayload(comments);
			}
		} catch (Exception e) {
			response.setErrorMessage("1008");
			e.printStackTrace(); 
		}
		return response.getResponseToJson();
	}

}
