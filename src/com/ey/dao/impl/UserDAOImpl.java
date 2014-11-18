package com.ey.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.dao.UserDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.UserBase;
import com.ey.entity.User;


@Repository("userDAO")  
public class UserDAOImpl extends BaseDAOImpl implements UserDAO {

	@Override
	public User findUserById(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		return (User)this.get(User.class, id);
		
	}

	@Override
	public void save(User user) throws RuntimeException {
		// TODO Auto-generated method stub
		super.save(user);
	}

	@Override
	public void delete(User user) throws RuntimeException {
		// TODO Auto-generated method stub
		super.delete(user);
	}

	@Override
	public void update(User user) throws RuntimeException {
		// TODO Auto-generated method stub
		super.update(user);
	}

	@Override
	public List<User> findUsers() throws RuntimeException {
		// TODO Auto-generated method stub
		String hql="from User order by id";
		return this.find(hql);
	}

	@Override
	public UserBase findUserByLoginCode(String loginCode, String password)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql="from UserBase where accountNumber = ? and passwd = ? order by id";
		List<UserBase> users = this.find(hql, new Object[]{loginCode,password});
		if(users!=null&&users.size()>0)
			return users.get(0);
		return null;
	}

	@Override
	public UserBase findUserByLoginCode(String loginCode) {
		String hql="from UserBase where accountNumber = ?";
		List<UserBase> users = this.find(hql, new Object[]{loginCode});
		if(users!=null&&users.size()>0)
			return users.get(0);
		return null;
	}

	@Override
	public void saveUser(UserBase user) {
		if(user.getId()==null){
			super.getDbId(user);
		}
		super.save(user);
	}

}
