package com.ey.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.bo.AgentBo;
import com.ey.bo.CountReportBo;
import com.ey.consts.SystemConst;
import com.ey.dao.AgentDAO;
import com.ey.dao.common.dbid.DbidGenerator;
import com.ey.dao.entity.AgentAccountRelation;
import com.ey.dao.entity.AgentAccountRelationId;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.AgentPaymentBatch;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.BankInfo;
import com.ey.service.AgentService;
import com.ey.util.CurrencyConverter;
import com.ey.util.DateUtil;
import com.ey.util.StringUtil;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service("agentService")
public class AgentServiceImpl implements AgentService {

	@Autowired
    private AgentDAO agentDAO;
	
	
	@Override
	public void saveAgent(AgentInfo agent,BankAccount bankAccount) throws RuntimeException {
		// TODO Auto-generated method stub
        agent.setId(DbidGenerator.getDbidGenerator().getNextId());
        agentDAO.save(agent);
        bankAccount.setId(DbidGenerator.getDbidGenerator().getNextId());
        agentDAO.save(bankAccount);
        agentDAO.save(new AgentAccountRelation(new AgentAccountRelationId(agent.getId(),bankAccount.getId()),false));
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
	public void updateAgent(AgentInfo agent,BankAccount bankAccount) throws RuntimeException {
		// TODO Auto-generated method stub
		agentDAO.update(agent);
		BankAccount account = (BankAccount)agentDAO.get(BankAccount.class, bankAccount.getId());
		if(account.getCardNumber().equalsIgnoreCase(bankAccount.getCardNumber())){
			BeanUtils.copyProperties(bankAccount, account);
			agentDAO.update(account);
		}
		else{
		 agentDAO.update(new AgentAccountRelation(new AgentAccountRelationId(agent.getId(),bankAccount.getId()),true));
		 bankAccount.setId(DbidGenerator.getDbidGenerator().getNextId());
		 agentDAO.save(bankAccount);
         agentDAO.save(new AgentAccountRelation(new AgentAccountRelationId(agent.getId(),bankAccount.getId()),false));
		}
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

	@Override
	public BankAccount getBankAccount(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.getBankAccount(id);
	}

	@Override
	public Map<String,Object> findUserByAreaId(String year,String areaId)
			throws RuntimeException {
		// TODO Auto-generated method stub
		List userlist = agentDAO.findUserByParam(areaId, null);
		Map<String,Object> monthMap = getUserNum(year,userlist);
        return monthMap;
	}

	@Override
	public AgentInfo getAgentByArea(String areaId) throws RuntimeException {
		
		return agentDAO.getAgentByArea(areaId);
	}

	@Override
	public List findBillNumByMonth(Long id, String month)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.findBillNumByMonth(id, month);
	}

	@Override
	public List findBillSettleByMonth(Long id, String month)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.findBillSettleByMonth(id, month);
	}
	@Override
	public List<CountReportBo> findReportByAgentId(Long id, String year, String areaId)
			throws RuntimeException {
		// TODO Auto-generated method stub
		List userlist = agentDAO.findUserByParam(areaId, year);
		Map<String,Object> monthMap = getUserNum(year,userlist);
		List<Object[]> reportlist = agentDAO.findPaymentBillByAgentId(id, year);
		Map<String,Object> billMap = getBillNum(year,reportlist);
		List<CountReportBo> repostlist = new ArrayList<CountReportBo>();
		Long yearTotal = (Long)monthMap.get(year);
		Double billYearTotal = (Double)billMap.get(year);
		Double userPercent = 0d;
		Double billPercent = 0d;
		Long userNum = null;
		Double billMoney = null;
	    for(Map.Entry<String,Object> entry:monthMap.entrySet()){
	    	   String key = entry.getKey();
	    	  if(key.equals("total")){
	    		  continue;
	    	  }
	    	   userNum = (Long)entry.getValue();
	    	   billMoney = (Double)billMap.get(key);
	    	  if(yearTotal!=0)
	    		  userPercent = Double.valueOf(userNum/yearTotal*100);
	    	  if(billYearTotal!=0)
	    		  billPercent = billMoney/billYearTotal*100;
	    	  repostlist.add(new CountReportBo(key,userNum+"",(String)CurrencyConverter.getPercent(userPercent),(String)CurrencyConverter.getMoney(billMoney),(String)CurrencyConverter.getPercent(billPercent)));  
	    }
		return repostlist;
	}
	private Map<String,Object> getUserNum(String year,List userlist){
		Map<String,Object> mapMonth = Collections.synchronizedMap(new LinkedHashMap<String,Object>());
		mapMonth.put(year, 0L);
		for(String m:SystemConst.MONTHS){
			mapMonth.put(year+"-"+m, 0L);
		}
		 String dataYearMonth = null;
		 String dataYear = null;
		 Long totalNum = 0L;
		for(Object o:userlist){
			dataYearMonth = DateUtil.getYearMonthNowString((Date)o);
			dataYear = String.valueOf(DateUtil.getYear((Date)o));
			Long monthNum = (Long)mapMonth.get(dataYearMonth);
			if(monthNum!=null){
				monthNum = monthNum + 1;
				mapMonth.put(dataYearMonth, monthNum);
			}
			if(dataYear.equals(year)){
				totalNum++;
			}
		}
		mapMonth.put(year, totalNum);
		mapMonth.put("total", Long.valueOf(userlist.size()));
		return mapMonth;
	}

	private Map<String,Object> getBillNum(String year,List<Object[]> billlist){
		Map<String,Object> mapMonth = Collections.synchronizedMap(new LinkedHashMap<String,Object>());
		mapMonth.put(year, 0d);
		for(String m:SystemConst.MONTHS){
			mapMonth.put(year+"-"+m, 0d);
		}
		 String dataYearMonth = null;
		 String dataYear = null;
		 Double totalMoney = 0d;
		 Double billMoney = 0d;
		for(Object[] o:billlist){
			dataYearMonth = DateUtil.getYearMonthNowString((Date)o[0]);
			dataYear = String.valueOf(DateUtil.getYear((Date)o[0]));
		    billMoney = (Double)o[1];
			Double monthMoney = (Double)mapMonth.get(dataYearMonth);
			if(monthMoney!=null){
				monthMoney = monthMoney + billMoney;
				mapMonth.put(dataYearMonth, monthMoney);
			}
			if(dataYear.equals(year)){
				totalMoney = totalMoney + billMoney;
			}
		}
		mapMonth.put(year, totalMoney);
		return mapMonth;
	}

	@Override
	public List findBillByCurrentDay(String currentDay)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.findBillByCurrentDay(currentDay);
	}

	@Override
	public void saveObject(Object o) throws RuntimeException {
		// TODO Auto-generated method stub
		agentDAO.save(o);
	}

	@Override
	public void savePaymentBatch(AgentPaymentBatch paymentBatch)
			throws RuntimeException {
		// TODO Auto-generated method stub
		paymentBatch.setId(DbidGenerator.getDbidGenerator().getNextId());
		agentDAO.save(paymentBatch);
	}

	@Override
	public void batchSaveObject(List objlist) throws RuntimeException {
		// TODO Auto-generated method stub
		agentDAO.batchSaveVO(objlist);
	}

	@Override
	public List findAgentSelf(Long id,Integer page,Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.findAgentSelf(id, page, rows);
	}

	@Override
	public List findBillByBatchId(Long id,Integer page,Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.findBillByBatchId(id, page, rows);
	}

	@Override
	public void updateBillStatusByIds(List<String> list)
			throws RuntimeException {
		// TODO Auto-generated method stub
		agentDAO.updateBillStatusByIds(list);
	}

	@Override
	public Long getTotalAgentByParam(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.getTotalAgentByParam(Qparam);
	}

}
