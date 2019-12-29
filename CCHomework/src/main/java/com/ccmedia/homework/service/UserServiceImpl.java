package com.ccmedia.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccmedia.homework.dao.UserDAOImpl;
import com.ccmedia.homework.model.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAOImpl userDAO;
	
	@Override
	public UserDTO checkLogin(UserDTO user) {
		return userDAO.checkLogin(user);
	}

	@Override
	public int signUp(UserDTO user) {
		return userDAO.signUp(user);
	}

}
