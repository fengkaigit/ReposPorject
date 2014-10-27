package com.ey.service;

import com.ey.entity.User;

public interface LoginService {
	public User findUserByLoginCode(String loginCode,String password) throws RuntimeException;

}
