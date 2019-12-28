package com.ccmedia.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccmedia.homework.dao.UserDAO;
import com.ccmedia.homework.model.UserDTO;

@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;
	
	public UserDTO checkLogin(UserDTO user) {
		return userDAO.checkLogin(user);
	}

	public int signUp(UserDTO user) {
		return userDAO.signUp(user);
	}

}
