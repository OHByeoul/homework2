package com.ccmedia.homework.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ccmedia.homework.model.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BoardDTO> getBoardList(Map params) {
		return sqlSession.selectList("getBoardList", params);
	}

	@Override
	public int getTotalCnt() {
		return sqlSession.selectOne("getTotalCnt");
	}

	@Override
	public BoardDTO getDetailContent(int boardId) {
		return sqlSession.selectOne("getDetailContent", boardId);
	}
	
	@Override
	public int createContent(BoardDTO board) {
		return sqlSession.insert("createContent", board);
	}
	
	@Override
	public int updateContent(BoardDTO board) {
		return sqlSession.update("updateContent", board);
	}
	
	@Override
	public int getCurrentId() {
		return sqlSession.selectOne("getCurrentId");
	}
	
	@Override
	public int deleteContent(int boardId) {
		return sqlSession.delete("deleteContent", boardId);
	}
	
	@Override
	public int setViews(int boardId) {
		return sqlSession.update("setViews", boardId);
		
	}
	
	@Override
	public List<BoardDTO> getSearchedList(String searchName) {
		return sqlSession.selectList("getSearchedList", searchName);
	}

}

