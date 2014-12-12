package com.ey.service;

import java.util.List;

import com.ey.dao.entity.NoticeInfo;
import com.ey.dao.entity.SysAnnouncement;
import com.ey.dao.entity.UserBase;
import com.ey.entity.User;

public interface UserService {
        
	public void saveObject(User user) throws RuntimeException;
	
	public void delete(User user) throws RuntimeException;
	
	public void update(User user) throws RuntimeException;
	
	public List<User> findUsers() throws RuntimeException;
	
	public User findUserById(Long id) throws RuntimeException;
	
	public UserBase findUserByLoginCode(String loginCode,String password) throws RuntimeException;
	
	public List<NoticeInfo> findNoticeByUserId(Long userId) throws RuntimeException;
	
	public List<SysAnnouncement> findSystemAnnounce(String areaId) throws RuntimeException;
}
