package com.ey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dao.UserDAO;
import com.ey.dao.entity.UserBase;
import com.ey.service.LoginService;


@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
    private UserDAO userDAO;
	
	@Override
	public UserBase findUserByLoginCode(String loginCode, String password)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return userDAO.findUserByLoginCode(loginCode, password);
	}
	@Override
	public UserBase findUserByLoginCode(String loginCode)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return userDAO.findUserByLoginCode(loginCode);
	}
	@Override
	public void saveUser(UserBase user) {
		// TODO Auto-generated method stub
		userDAO.saveUser(user);
	}
}
