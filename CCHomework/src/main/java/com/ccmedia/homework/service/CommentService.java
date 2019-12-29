package com.ccmedia.homework.service;

import java.util.List;

import com.ccmedia.homework.model.CommentDTO;
import com.ccmedia.homework.model.ResponseContainer;

public interface CommentService {
	public String getComments(String boardId,  ResponseContainer<List> response);
	
	public String createComment(CommentDTO commentDTO, ResponseContainer<List> response);
	
	
}
