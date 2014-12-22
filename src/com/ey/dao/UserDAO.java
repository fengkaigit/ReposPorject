package com.ey.dao;

import java.util.List;

import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.NoticeInfo;
import com.ey.dao.entity.SysAnnouncement;
import com.ey.dao.entity.UserBase;
import com.ey.entity.User;

public interface UserDAO extends BaseDAO{
	
    public void save(User user) throws RuntimeException;
	
	public void delete(User user) throws RuntimeException;
	
	public void update(User user) throws RuntimeException;
	
	public List<User> findUsers() throws RuntimeException;
	
	public User findUserById(Long id) throws RuntimeException;
	
	public UserBase findUserByLoginCode(String loginCode,String password) throws RuntimeException;

	public UserBase findUserByLoginCode(String loginCode) throws RuntimeException;

	public void saveUser(UserBase user) throws RuntimeException;
	
	public List<NoticeInfo> findNoticeByUserId(Long userId, Integer showCount, Integer page) throws RuntimeException;
	
	public List<SysAnnouncement> findSystemAnnounce(String areaId, Long id) throws RuntimeException;
	
	public void modifyUserMessage(Integer status, Long userId, Long id) throws RuntimeException;
	
	public void updatePasswd(UserBase user) throws RuntimeException;
    
}
