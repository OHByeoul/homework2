package com.ccmedia.homework.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ccmedia.homework.model.CommentDTO;

@Repository
public class CommentDAO {
	@Autowired
	private SqlSession sqlSession;
	
	public List<CommentDTO> getComments(String boardId) {
		return sqlSession.selectList("getComments", boardId);
	}

	public int createComment(CommentDTO commentDTO) {
		return sqlSession.insert("createComment", commentDTO);
	}

}
