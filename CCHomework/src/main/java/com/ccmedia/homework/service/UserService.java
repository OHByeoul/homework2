package com.ccmedia.homework.service;

import com.ccmedia.homework.model.UserDTO;

public interface UserService {
	public UserDTO checkLogin(UserDTO user);

	public int signUp(UserDTO user);
}
