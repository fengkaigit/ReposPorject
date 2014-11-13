package com.ey.service;

import com.ey.dao.entity.UserBase;

public interface LoginService {
	public UserBase findUserByLoginCode(String loginCode,String password) throws RuntimeException;
	public UserBase findUserByLoginCode(String loginCode) throws RuntimeException;
	public void saveUser(UserBase user);
}
