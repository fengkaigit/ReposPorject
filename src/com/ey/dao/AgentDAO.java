package com.ey.dao;

import java.util.List;
import java.util.Map;

import com.ey.bo.AgentBo;
import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.BankAccount;

public interface AgentDAO extends BaseDAO {
	
    void deleteByAgentId(Long id) throws RuntimeException;
   
    List<AgentBo> getAllAgent(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
    
    AgentBo getAgent(Long id) throws RuntimeException;
    
    AgentBo findAgentByLoginName(String loginName,String password) throws RuntimeException;
    
    Long findAgentByLoginName(String loginName) throws RuntimeException;
    
	void updatePassById(Long id,String password) throws RuntimeException;
	
	BankAccount getBankAccount(Long id) throws RuntimeException;


}
