package com.ccmedia.homework.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ccmedia.homework.model.UserDTO;

@Repository
public class UserDAO {
	@Autowired
	private SqlSession sqlSession;
	
	public UserDTO checkLogin(UserDTO user) {
		return sqlSession.selectOne("checkLogin",user);
	}

	public int signUp(UserDTO user) {
		return sqlSession.insert("signUp",user); 
	}

}
