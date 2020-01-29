package com.ccmedia.homework.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ccmedia.homework.model.NoticeDTO;
import com.ccmedia.homework.model.ProjectDTO;

@Repository
public class ProjectDAOImpl implements ProjectDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int createProjectContent(ProjectDTO project) {
		return sqlSession.insert("createProjectContent", project);
	}

	@Override
	public ProjectDTO getProjectDetailContent(int projectId) {
		return sqlSession.selectOne("getProjectDetailContent", projectId);
	}

	@Override
	public int updateProjectContent(ProjectDTO project) {
		return sqlSession.update("updateProjectContent", project);
	}
	
	@Override
	public int getCurrentNoticeId() {
		return sqlSession.selectOne("getCurrentNoticeId");
	}
	
	@Override
	public List<ProjectDTO> getProjectList(Map params) {
		return sqlSession.selectList("getProjectList", params);
	}

	@Override
	public int getTotalCnt() {
		return sqlSession.selectOne("getTotalProjectCnt");
	}


	@Override
	public int deleteContent(int projectId) {
		return sqlSession.delete("deleteProjectContent", projectId);
	}

}

