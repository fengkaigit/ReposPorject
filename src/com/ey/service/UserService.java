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
	
	public List<NoticeInfo> findNoticeByUserId(Long userId, Integer showCount, Integer page) throws RuntimeException;
	
	public List<SysAnnouncement> findSystemAnnounce(String pareaId,String areaId, Long id, String showDate) throws RuntimeException;
	
	public void modifyUserMessage(Integer status, Long userId,Long id) throws RuntimeException;
	
	public void updatePasswd(UserBase user) throws RuntimeException;
}
