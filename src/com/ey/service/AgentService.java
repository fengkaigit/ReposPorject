package com.ey.service;

import java.util.List;
import java.util.Map;

import com.ey.bo.AgentBo;
import com.ey.bo.CountReportBo;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.AgentPaymentBatch;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.BankInfo;
import com.ey.dao.entity.NoticeInfo;
import com.ey.dao.entity.SystemManager;

public interface AgentService {
	
      void saveAgent(AgentInfo agent,BankAccount bankAccount) throws RuntimeException;
      
      void updateAgent(AgentInfo agent,BankAccount bankAccount) throws RuntimeException;
      
      void deleteByAgentIds(String[] ids) throws RuntimeException;
      
      List<AgentBo> getAllAgent(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
      
      AgentBo getAgentBo(Long id) throws RuntimeException;
      
      AgentInfo getAgent(Long id) throws RuntimeException;

      
      AgentBo findAgentByLoginName(String loginName,String password) throws RuntimeException;
      
      Long findAgentByLoginName(String loginName) throws RuntimeException;
      
  	  void updatePassById(Long id,String password) throws RuntimeException;
  	  
  	  BankAccount getBankAccount(Long id) throws RuntimeException;
  	  
  	  Map<String,Object> findUserByAreaId(String year,String areaId) throws RuntimeException;
  	  
  	 List<CountReportBo> findReportByAgentId(Long id,String year,String areaId) throws RuntimeException;
  	  
  	 List findBillNumByMonth(Long id,String month) throws RuntimeException;
  	 
  	 List findBillSettleByMonth(Long id,String month) throws RuntimeException;
  	 
  	 List findBillByCurrentDay(String currentDay) throws RuntimeException;
  	  
  	  AgentInfo getAgentByArea(String areaId) throws RuntimeException;

     void saveObject(Object o) throws RuntimeException;
     
     void saveNotice(NoticeInfo o) throws RuntimeException;
     
     void batchSaveObject(List objlist) throws RuntimeException;
     
     void savePaymentBatch(AgentPaymentBatch paymentBatch) throws RuntimeException;
     
     List findAgentSelf(Long id,Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
     
     Long findAgentSelfTotal(Long id,Map<String,Object> Qparam) throws RuntimeException;
     
     List findBillByBatchId(Long id,Map<String,Object> Qparam ,Integer page,Integer rows) throws RuntimeException;
     
     Long findBillTotalBatchId(Long id,Map<String,Object> Qparam) throws RuntimeException;
     
     void updateBillStatusByIds(List<String> list,Integer status) throws RuntimeException;
     
     Long getTotalAgentByParam(Map<String,Object> Qparam) throws RuntimeException;
     
     void updateStatusByBatchId(Long id,Integer status) throws RuntimeException;
     
     void createAgentBatch(Map<Integer,String> payTypesMap) throws RuntimeException;
     
}
