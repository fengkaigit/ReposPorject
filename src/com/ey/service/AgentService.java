package com.ey.service;

import java.util.List;
import java.util.Map;

import com.ey.bo.AgentBo;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.SystemManager;

public interface AgentService {
	
      void saveAgent(AgentInfo agent) throws RuntimeException;
      
      void updateAgent(AgentInfo agent) throws RuntimeException;
      
      void deleteByAgentIds(String[] ids) throws RuntimeException;
      
      List<AgentBo> getAllAgent(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
      
      AgentBo getAgent(Long id) throws RuntimeException;
      
      AgentBo findAgentByLoginName(String loginName,String password) throws RuntimeException;
      
      Long findAgentByLoginName(String loginName) throws RuntimeException;
      
  	  void updatePassById(Long id,String password) throws RuntimeException;



}
