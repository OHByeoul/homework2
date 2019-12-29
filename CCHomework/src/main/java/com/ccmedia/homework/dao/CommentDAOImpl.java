package com.ccmedia.homework.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ccmedia.homework.model.CommentDTO;

@Repository
public class CommentDAOImpl implements CommentDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<CommentDTO> getComments(String boardId) {
		return sqlSession.selectList("getComments", boardId);
	}

	@Override
	public int createComment(CommentDTO commentDTO) {
		return sqlSession.insert("createComment", commentDTO);
	}

}
