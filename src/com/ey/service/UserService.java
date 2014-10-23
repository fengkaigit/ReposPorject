package com.ey.service;

import java.util.List;

import com.ey.entity.User;

public interface UserService {
        
	public void saveObject(User user) throws RuntimeException;
	
	public void delete(User user) throws RuntimeException;
	
	public void update(User user) throws RuntimeException;
	
	public List<User> findUsers() throws RuntimeException;
	
	public User findUserById(Long id) throws RuntimeException;
	
	public User findUserByLoginCode(String loginCode,String password) throws RuntimeException;
	  
}
