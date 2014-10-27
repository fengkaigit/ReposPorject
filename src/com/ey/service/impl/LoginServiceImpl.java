package com.ey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dao.UserDAO;
import com.ey.entity.User;
import com.ey.service.LoginService;


@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
    private UserDAO userDAO;
	
	@Override
	public User findUserByLoginCode(String loginCode, String password)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return userDAO.findUserByLoginCode(loginCode, password);
	}

}
