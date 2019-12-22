package com.ccmedia.homework.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ccmedia.homework.model.Board;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSession sqlSession;

	public List<Board> getBoardList() {
		return sqlSession.selectList("getBoardList");
	}

}
