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
	
	List<Object> findUserByParam(String areaId,String year) throws RuntimeException;

	List findPaymentBillByAgentId(Long id,String year) throws RuntimeException;
	
 	 List findBillNumByMonth(Long id,String month) throws RuntimeException;
 	 
 	 List findBillSettleByMonth(Long id,String month) throws RuntimeException;
 	 
  	 List findBillByCurrentDay(String currentDay) throws RuntimeException;
  	 
     List findAgentSelf(Long id,Integer page,Integer rows) throws RuntimeException;
     
     List findBillByBatchId(Long id,Integer page,Integer rows) throws RuntimeException;



}
