package com.ey.dao.impl;

import org.springframework.stereotype.Repository;

import com.ey.dao.SysManDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.Feedback;
import com.ey.dao.entity.SystemManager;
import com.ey.util.StringUtil;

import java.util.ArrayList;
import java.util.Date;
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
		String hql = "from SystemManager a where a.managerName = ? and a.passwd = ?";
		List<SystemManager> sysMans = this.find(hql,new Object[]{loginName,password});
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

	@Override
	public Long findManagerByLoginName(String loginName)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select count(id) from SystemManager a where a.managerName = ?";
		return this.count(hql, new Object[]{loginName});
	}

	@Override
	public void updateReplyFeedById(Long id, String replyContent)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "update Feedback set systemTime = ?,systemFeedback = ?,backFlag = ? where id = ?";
		this.executeHql(hql, new Object[]{new Date(),replyContent,1,id});
	}

	@Override
	public java.util.List<Feedback> findFeedBacks(Map<String, Object> Qparam,
			Integer page, Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("from Feedback where 1=1");
		createQueryParam(hql,Qparam,paramList);
		hql.append(" order by viewTime desc,areaId");
		return this.find(hql.toString(),paramList.toArray(), page, rows);
	}

	@Override
	public  void deleteFeedBackById(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "delete from Feedback where id = ?";
		this.executeHql(hql, new Object[]{id});
	}
	
	private void createQueryParam(StringBuffer query,Map<String, Object> Qparam,List paramList){
		if(Qparam!=null&&Qparam.size()>0){
			Long userId = (Long)Qparam.get("userId");
			Integer backFlag = (Integer)Qparam.get("backFlag");
			String areaId = (String)Qparam.get("areaId");
			if(userId!=null){
				query.append(" and userId = ?");
				paramList.add(userId);
			}
			if(backFlag!=null){
				query.append(" and backFlag = ?");
				paramList.add(backFlag);
			}
			if(areaId!=null){
				query.append(" and areaId = ?");
				paramList.add(areaId);
			}
		}
	}

	@Override
	public Long findTotalFeedBack(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("select count(id) from Feedback where 1=1");
		createQueryParam(hql,Qparam,paramList);
		List list = this.find(hql.toString(),paramList.toArray());
		if(list!=null&&list.size()>0){
			return Long.valueOf(list.get(0)+"");
		}
		return 0l;
	}

}
