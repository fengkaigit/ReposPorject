package com.ey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ey.dao.base.BaseDAO;
import com.ey.entity.User;
import com.ey.service.UserService;


@Service("userService")
//@Transactional(propagation = Propagation.REQUIRED, readOnly = false,rollbackFor = RuntimeException.class)
public class UserServiceImpl implements UserService {
	
     @Autowired
     private BaseDAO<User> baseDAO;

	@Override
	public void saveObject(User user) throws RuntimeException {
		// TODO Auto-generated method stub
		 baseDAO.saveOrUpdate(user);
	}

	@Override
	public void delete(User user) throws RuntimeException{
		// TODO Auto-generated method stub
		baseDAO.delete(user);
	}

	@Override
	public void update(User user) throws RuntimeException{
		// TODO Auto-generated method stub
		baseDAO.update(user);
	}

	@Override
	public List<User> findUsers() throws RuntimeException{
		// TODO Auto-generated method stub
		String hql="from User order by id";
		return baseDAO.find(hql);
	}

	@Override
	public User findUserById(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		return baseDAO.get(User.class, id);
	}

	@Override
	public User findUserByLoginCode(String loginCode,String password) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql="from User where loginCode = ? and password = ? order by id";
		List<User> users = baseDAO.find(hql, new Object[]{loginCode,password});
		if(users!=null&&users.size()>0)
			return users.get(0);
		return null;
	}
}
