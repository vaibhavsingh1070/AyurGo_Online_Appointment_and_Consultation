package com.AyurGo.service;

import java.util.List;

import com.AyurGo.entity.User;

public interface UserService {

	public User saveUser(User user);

	public void removeSessionMessage();
	
	public List<User> listAll();

}
