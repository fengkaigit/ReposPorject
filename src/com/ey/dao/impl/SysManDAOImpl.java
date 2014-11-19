package com.ey.dao.impl;

import org.springframework.stereotype.Repository;

import com.ey.dao.SysManDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.SystemManager;
import com.ey.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("sysManDAO")
public class SysManDAOImpl extends BaseDAOImpl implements SysManDAO {

	@Override
	public void deleteByManId(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
        String hql = "delete from SystemManager where id = ?";
        this.executeHql(hql, new Object[]{id});
	}

	@Override
	public List<SystemManager> findManagers(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		//List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("from SystemManager a where 1=1");
		return this.find(hql.toString(), page, rows);
	}

	@Override
	public SystemManager findManagerByLoginName(String loginName,
			String password) throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("from SystemManager a where a.managerName = ?");
		paramList.add(loginName);
		if(!StringUtil.isEmptyString(password)){
		     hql.append( "and a.passwd = ?");
		     paramList.add(password);
		}
		List<SystemManager> sysMans = this.find(hql.toString(), paramList.toArray());
		if(sysMans!=null&&sysMans.size()>0)
			return sysMans.get(0);
		return null;
	}

	@Override
	public void updatePassById(Long id, String password)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "update SystemManager set passwd = ? where id = ?";
		this.executeHql(hql, new Object[]{password,id});
	}

}
