package com.ey.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.bo.AgentBo;
import com.ey.dao.AgentDAO;
import com.ey.dao.common.dbid.DbidGenerator;
import com.ey.dao.entity.AgentInfo;
import com.ey.service.AgentService;
import com.ey.util.StringUtil;

@Service("agentService")
public class AgentServiceImpl implements AgentService {

	@Autowired
    private AgentDAO agentDAO;
	
	
	@Override
	public void saveAgent(AgentInfo agent) throws RuntimeException {
		// TODO Auto-generated method stub
        agent.setId(DbidGenerator.getDbidGenerator().getNextId());
           agentDAO.save(agent);
	}

	@Override
	public void deleteByAgentIds(String[] ids) throws RuntimeException {
		// TODO Auto-generated method stub
            for(String id:ids){
            	if(StringUtil.isEmptyString(id))
            		continue;
            	agentDAO.deleteByAgentId(Long.valueOf(id.trim()));
            }
	}

	@Override
	public List<AgentBo> getAllAgent(Map<String, Object> Qparam, Integer page,
			Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.getAllAgent(Qparam, page, rows);
	}

	@Override
	public AgentBo getAgentBo(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.getAgent(id);
	}

	@Override
	public void updateAgent(AgentInfo agent) throws RuntimeException {
		// TODO Auto-generated method stub
		agentDAO.update(agent);
	}

	@Override
	public AgentBo findAgentByLoginName(String loginName, String password)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.findAgentByLoginName(loginName, password);
	}

	@Override
	public Long findAgentByLoginName(String loginName)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.findAgentByLoginName(loginName);
	}

	@Override
	public void updatePassById(Long id, String password)
			throws RuntimeException {
		// TODO Auto-generated method stub
		agentDAO.updatePassById(id, password);
	}

	@Override
	public AgentInfo getAgent(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		return (AgentInfo)agentDAO.get(AgentInfo.class, id);
	}

}
