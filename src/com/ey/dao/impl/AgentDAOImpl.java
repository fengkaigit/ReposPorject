package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.bo.AgentBo;
import com.ey.dao.AgentDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.SystemManager;
import com.ey.util.StringUtil;

@Repository("agentDAO")
public class AgentDAOImpl extends BaseDAOImpl implements AgentDAO {

	@Override
	public void deleteByAgentId(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
         String hql = "update from AgentInfo set delFlag = ? where id = ?";
         this.executeHql(hql, new Object[]{true,id});
	}

	@Override
	public List<AgentBo> getAllAgent(Map<String, Object> Qparam,
			Integer page, Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		//List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("select new com.ey.bo.AgentBo(a.id,a.registAccount,a.passwd,a.EMail,a.mobile,a.rebackDot,a.registRealName,a.areaId,b.province,b.namePath,b.encodePath) from AgentInfo a,Area b where a.areaId = b.id and a.delFlag = 0");
		hql.append(" order by a.areaId");
		return this.find(hql.toString(), page, rows);
	}

	@Override
	public AgentBo getAgent(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select new com.ey.bo.AgentBo(a.id,a.registAccount,a.passwd,a.EMail,a.mobile,a.rebackDot,a.registRealName,a.areaId,b.province,b.namePath,b.encodePath) from AgentInfo a,Area b where a.areaId = b.id and a.id = ?";
		List<AgentBo> list = this.find(hql, new Object[]{id});
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public AgentBo findAgentByLoginName(String loginName, String password)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select new com.ey.bo.AgentBo(a.id,a.registAccount,a.passwd,a.EMail,a.mobile,a.rebackDot,a.registRealName,a.areaId,b.province,b.namePath,b.encodePath) from AgentInfo a,Area b where a.areaId = b.id and a.registAccount = ? and a.passwd = ? and a.delFlag = 0";
		List<AgentBo> agents = this.find(hql.toString(),new Object[]{loginName,password});
		if(agents!=null&&agents.size()>0)
			return agents.get(0);
		return null;
	}

	@Override
	public Long findAgentByLoginName(String loginName) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select count(id) from AgentInfo a where a.registAccount = ? and a.delFlag = 0";
		return this.count(hql, new Object[]{loginName});
	}

	@Override
	public void updatePassById(Long id, String password)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "update AgentInfo set passwd = ? where id = ?";
		this.executeHql(hql, new Object[]{password,id});
	}

	@Override
	public BankAccount getBankAccount(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select b from AgentAccountRelation a,BankAccount b where a.id.bankAccountId = b.id and a.id.id = ? and a.flag = ?";
		List<BankAccount> list = this.find(hql, new Object[]{id,false});
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

}
