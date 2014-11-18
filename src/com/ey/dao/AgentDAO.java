package com.ey.dao;

import java.util.List;
import java.util.Map;

import com.ey.bo.AgentBo;
import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.AgentInfo;

public interface AgentDAO extends BaseDAO {
	
    void deleteByAgentId(Long id) throws RuntimeException;
   
    List<AgentBo> getAllAgent(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
    
    AgentBo getAgent(Long id) throws RuntimeException;
}
