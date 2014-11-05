package com.ey.dao;

import java.util.List;

import com.ey.entity.User;
import com.ey.dao.base.BaseDAO;

public interface UserDAO extends BaseDAO{
	
    public void save(User user) throws RuntimeException;
	
	public void delete(User user) throws RuntimeException;
	
	public void update(User user) throws RuntimeException;
	
	public List<User> findUsers() throws RuntimeException;
	
	public User findUserById(Long id) throws RuntimeException;
	
	public User findUserByLoginCode(String loginCode,String password) throws RuntimeException;
    
}
