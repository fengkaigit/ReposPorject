package com.ey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dao.UserDAO;
import com.ey.dao.common.dbid.DbidGenerator;
import com.ey.dao.entity.NoticeInfo;
import com.ey.dao.entity.SysAnnouncement;
import com.ey.dao.entity.UserBase;
import com.ey.entity.User;
import com.ey.entity.UserOrgan;
import com.ey.service.UserService;


@Service("userService")
//@Transactional(propagation = Propagation.REQUIRED, readOnly = false,rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService {
	
     @Autowired
     private UserDAO userDAO;

	@Override
	public void saveObject(User user) throws RuntimeException {
		// TODO Auto-generated method stub
		user.setId(DbidGenerator.getDbidGenerator().getNextId());
		userDAO.save(user);
		userDAO.saveOrUpdate(new UserOrgan(150105L,user.getId()));
	}

	@Override
	public void delete(User user) throws RuntimeException{
		// TODO Auto-generated method stub
		userDAO.delete(user);
	}

	@Override
	public void update(User user) throws RuntimeException{
		// TODO Auto-generated method stub
		userDAO.update(user);
	}

	@Override
	public List<User> findUsers() throws RuntimeException{
		// TODO Auto-generated method stub
		return userDAO.findUsers();
	}

	@Override
	public User findUserById(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		return userDAO.findUserById(id);
	}

	@Override
	public UserBase findUserByLoginCode(String loginCode,String password) throws RuntimeException {
		// TODO Auto-generated method stub
		return userDAO.findUserByLoginCode(loginCode, password);
	}

	@Override
	public List<NoticeInfo> findNoticeByUserId(Long userId, Integer showCount, Integer page) throws RuntimeException {
		// TODO Auto-generated method stub
		return userDAO.findNoticeByUserId(userId, showCount, page);
	}

	@Override
	public List<SysAnnouncement> findSystemAnnounce(String areaId, Long id)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return userDAO.findSystemAnnounce(areaId,id);
	}

	@Override
	public void modifyUserMessage(Integer status, Long userId, Long id)
			throws RuntimeException {
		userDAO.modifyUserMessage(status, userId, id);
	}

	@Override
	public void updatePasswd(UserBase user) throws RuntimeException {
		userDAO.updatePasswd(user);
	}
}
