package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.dao.UserDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.NoticeInfo;
import com.ey.dao.entity.SysAnnouncement;
import com.ey.dao.entity.UserBase;
import com.ey.entity.User;
import com.ey.util.DateUtil;


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

	@Override
	public List<NoticeInfo> findNoticeByUserId(Long userId, Integer showCount, Integer page)
			throws RuntimeException {
		String hql = "from NoticeInfo where userId=? and sendStatus=?";
		return this.find(hql, new Object[]{userId, 0}, page, showCount);
	}

	@Override
	public java.util.List<SysAnnouncement> findSystemAnnounce(String pareaId,String areaId, Long id, String showDate)
			throws RuntimeException {
		String hql = "from SysAnnouncement where status=? and announcementGroup=?";
		List paramList = new ArrayList();
		paramList.add(0);
		paramList.add(1);
		if (areaId!=null){
			hql = hql + " and (announcementScope=? or (announcementScope=? and areaId = ?) or (announcementScope=? and areaId = ?) )";
			paramList.add(0);
			paramList.add(1);
			paramList.add(areaId);
			paramList.add(1);
			paramList.add(pareaId);
		}
		if (id!=null){
			hql = hql + " and id=?";
			paramList.add(id);
		}
		if (showDate!=null){
			hql = hql + " and retentionTime>=?";
			paramList.add(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss",showDate+" 00:00:00"));
		}
		return this.find(hql, paramList.toArray());
	}

	@Override
	public void modifyUserMessage(Integer status, Long userId, Long id)
			throws RuntimeException {
		String hql = "update from NoticeInfo set sendStatus  = ? where userId=? and id = ?";
        this.executeHql(hql, new Object[]{status, userId,id});
	}

	@Override
	public void updatePasswd(UserBase user) throws RuntimeException {
		String hql = " update from UserBase set passwd=? where id=?";
		this.executeHql(hql, new Object[]{user.getPasswd(), user.getId()});
	}

}
