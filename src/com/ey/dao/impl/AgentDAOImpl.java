package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.bo.AgentBo;
import com.ey.dao.AgentDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.AgentInfo;

@Repository("agentDAO")
public class AgentDAOImpl extends BaseDAOImpl implements AgentDAO {

	@Override
	public void deleteByAgentId(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
         String hql = "delete from AgentInfo where id = ?";
         this.executeHql(hql, new Object[]{id});
	}

	@Override
	public List<AgentBo> getAllAgent(Map<String, Object> Qparam,
			Integer page, Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("select new com.ey.bo.AgentBo(a.id,a.registAccount,a.passwd,a.EMail,a.mobile,a.areaId,b.province) from AgentInfo a,Area b where a.areaId = b.id");
		return this.find(hql.toString(), paramList, page, rows);
	}

	@Override
	public AgentBo getAgent(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select new com.ey.bo.AgentBo(a.id,a.registAccount,a.passwd,a.EMail,a.mobile,a.areaId,b.province) from AgentInfo a,Area b where a.areaId = b.id and a.id = ?";
		List<AgentBo> list = this.find(hql, new Object[]{id});
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

}
