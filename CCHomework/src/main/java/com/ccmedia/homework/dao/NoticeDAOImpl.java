package com.ccmedia.homework.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ccmedia.homework.model.BoardDTO;
import com.ccmedia.homework.model.NoticeDTO;

@Repository
public class NoticeDAOImpl implements NoticeDAO {
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
	
	@Override
	public List<NoticeDTO> getNoticeList(Map params) {
		return sqlSession.selectList("getNoticeList", params);
	}

	@Override
	public int getTotalCnt() {
		return sqlSession.selectOne("getTotalNoticeCnt");
	}


	@Override
	public int deleteContent(int noticeId) {
		return sqlSession.delete("deleteNoticeContent", noticeId);
	}


}

