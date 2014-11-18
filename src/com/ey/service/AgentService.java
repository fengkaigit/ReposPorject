package com.ey.service;

import java.util.List;
import java.util.Map;

import com.ey.bo.AgentBo;
import com.ey.dao.entity.AgentInfo;

public interface AgentService {
	
      void saveAgent(AgentInfo agent) throws RuntimeException;
      
      void deleteByAgentIds(String[] ids) throws RuntimeException;
      
      List<AgentBo> getAllAgent(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
      
      AgentBo getAgent(Long id) throws RuntimeException;
}
