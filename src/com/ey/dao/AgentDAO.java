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

	AgentInfo getAgentByArea(String areaId) throws RuntimeException;
	
	List findPaymentBillByAgentId(Long id,String year) throws RuntimeException;
	
 	 List findBillNumByMonth(Long id,String month) throws RuntimeException;
 	 
 	 List findBillSettleByMonth(Long id,String month) throws RuntimeException;
 	 
  	 List findBillByCurrentDay(String currentDay) throws RuntimeException;
  	 
     List findAgentSelf(Long id,Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
     
     Long findAgentSelfTotal(Long id,Map<String,Object> Qparam) throws RuntimeException;
     
     List findBillByBatchId(Long id,Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
     
     Long findBillTotalBatchId(Long id,Map<String,Object> Qparam) throws RuntimeException;

     void updateBillStatusByIds(List<String> list,Integer status) throws RuntimeException;
     
     Long getTotalAgentByParam(Map<String,Object> Qparam) throws RuntimeException;
     
     void updateStatusByBatchId(Long id,Integer status) throws RuntimeException;
     
     List findAgentSignRateByAgentId(Long id) throws RuntimeException;
     void updateSignRateById(Long id,Double rate) throws RuntimeException;
     void updateErrorFlagByBatchId(Long batchId,Boolean flag)throws RuntimeException;




}
