package com.ccmedia.homework.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ccmedia.homework.model.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;

	public List<BoardDTO> getBoardList() {
		return sqlSession.selectList("getBoardList");
	}

	@Override
	public String getTotalCnt() {
		return sqlSession.selectOne("getTotalCnt");
	}

}
