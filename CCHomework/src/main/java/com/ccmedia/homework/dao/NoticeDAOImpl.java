package com.ccmedia.homework.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ccmedia.homework.model.NoticeDTO;

@Repository
public class NoticeDAOImpl implements NoticeDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int createNoticeContent(NoticeDTO notice) {
		return sqlSession.insert("createNoticeContent", notice);
	}

	@Override
	public NoticeDTO getNoticeDetailContent(int noticeId) {
		return sqlSession.selectOne("getNoticeDetailContent", noticeId);
	}

	@Override
	public int updateNoticeContent(NoticeDTO notice) {
		return sqlSession.update("updateNoticeContent", notice);
	}
	
	@Override
	public int getCurrentNoticeId() {
		return sqlSession.selectOne("getCurrentNoticeId");
	}

}
