package com.ccmedia.homework.dao;

import java.util.List;

import com.ccmedia.homework.model.CommentDTO;

public interface CommentDAO {
	public List<CommentDTO> getComments(String boardId);

	public int createComment(CommentDTO commentDTO);
}
