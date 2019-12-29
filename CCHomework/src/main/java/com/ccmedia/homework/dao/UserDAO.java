package com.ccmedia.homework.dao;

import com.ccmedia.homework.model.UserDTO;

public interface UserDAO {
	public UserDTO checkLogin(UserDTO user);

	public int signUp(UserDTO user);
}
