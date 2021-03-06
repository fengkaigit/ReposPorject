package com.ey.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
import com.ey.dao.entity.AgentSignRate;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.BankInfo;
import com.ey.dao.entity.BatchPaymentRelation;
import com.ey.dao.entity.BatchPaymentRelationId;
import com.ey.dao.entity.NoticeInfo;
import com.ey.dao.entity.PaymentHedge;
import com.ey.service.AgentService;
import com.ey.util.CurrencyConverter;
import com.ey.util.DateUtil;
import com.ey.util.StringUtil;
import java.util.Date;


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
        if(agent.getSignDate()!=null&&agent.getSignPeriod()!=null){
        	createSignPeriod(agent);
        }
	}
	private void createSignPeriod(AgentInfo agent){
		int singNum = agent.getSignPeriod().intValue();
		Date signBeginDate = agent.getSignDate();
		Date signEndDate = null;
		List<AgentSignRate> volist = new ArrayList<AgentSignRate>();
		for(int i = 1;i<=singNum;i++){
			AgentSignRate signRate = new AgentSignRate();
			signRate.setId(DbidGenerator.getDbidGenerator().getNextId());
			signRate.setAgentId(agent.getId());
			signRate.setBeginTime(signBeginDate);
			signBeginDate = DateUtil.getAfterYear(signBeginDate, 1);
			signEndDate = DateUtil.getAfterDay(signBeginDate,-1);
			signRate.setSignRate(0d);
			signRate.setEndTime(signEndDate);
			volist.add(signRate);
		}
		agentDAO.batchSaveVO(volist);
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
	public List<CountReportBo> findReportByAgentId(Long id, String year, Map<String,Object> monthMap)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return getReports(id,year,monthMap);
	}
	private List<CountReportBo> getReports(Long id,String year,Map<String,Object> monthMap){
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
	public List findAgentSelf(Long id,Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.findAgentSelf(id, Qparam,page, rows);
	}

	@Override
	public List findBillByBatchId(Long id,Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.findBillByBatchId(id,Qparam, page, rows);
	}

	@Override
	public void updateBillStatusByIds(List<String> list,Integer status)
			throws RuntimeException {
		// TODO Auto-generated method stub
		agentDAO.updateBillStatusByIds(list,status);
	}

	@Override
	public Long getTotalAgentByParam(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.getTotalAgentByParam(Qparam);
	}

	@Override
	public Long findBillTotalBatchId(Long id, Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.findBillTotalBatchId(id, Qparam);
	}

	@Override
	public Long findAgentSelfTotal(Long id, Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.findAgentSelfTotal(id, Qparam);
	}

	@Override
	public void updateStatusByBatchId(Long id, Integer status)
			throws RuntimeException {
		// TODO Auto-generated method stub
		agentDAO.updateStatusByBatchId(id, status);
	}

	@Override
	public void saveNotice(Map<String,Object> paramMap,NoticeInfo o) throws RuntimeException {
		// TODO Auto-generated method stub
		Long billId = (Long)paramMap.get("billId");
		Integer billStatus = (Integer)paramMap.get("payerrStatus");
		final Long batchId = (Long)paramMap.get("batchId");
		agentDAO.updateBillStatusByIds(Arrays.asList(new String[]{billId+""}), billStatus);
		agentDAO.updateErrorFlagByBatchId(batchId,billId,true);
		o.setId(DbidGenerator.getDbidGenerator().getNextId());
		agentDAO.save(o);
		if(billStatus==SystemConst.REFUND){
			Double paidMoney = (Double)paramMap.get("paidMoney");
			PaymentHedge paymentHedge = new PaymentHedge();
			paymentHedge.setId(DbidGenerator.getDbidGenerator().getNextId());
			paymentHedge.setBillId(billId);
			paymentHedge.setHedgeMoney(paidMoney);
			paymentHedge.setCreateTime(new Date());
			paymentHedge.setStatisStatus(0);
			paymentHedge.setHedgeType(2);
			agentDAO.save(paymentHedge);
		}
		Long num = agentDAO.findBillTotalBatchId(batchId, new HashMap<String,Object>(){
		     {
		    	 put("batchId", batchId);
		    	 put("paymentStatus",3);
		     }
		     });
		if(num!=null&&num==0){
				agentDAO.updateStatusByBatchId(batchId, 1);
		}
	}

	@Override
	public boolean createAgentBatch(Map<Integer,String> payTypesMap) throws RuntimeException {
		// TODO Auto-generated method stub
		boolean isCreate = false;
		List<Object[]> billlist = this.findBillByCurrentDay(null);
		if (billlist != null && billlist.size() > 0) {
			Date date = new Date();
			List<BatchPaymentRelation> batchpaylist = new ArrayList<BatchPaymentRelation>();
			List<String> billIdsList = new ArrayList<String>();
			for (Object[] o : billlist) {
				AgentPaymentBatch paymentBatch = new AgentPaymentBatch(
						(Double) o[3], date, 0, (Long) o[1],
						(Integer) o[0], (Long) o[4],payTypesMap.get((Integer)o[0]),false,0l,false,(String)o[5],(String)o[6],(String)o[7]);
				this.savePaymentBatch(paymentBatch);
				String[] billIds = ((String) o[2])
						.split(SystemConst.SPLITE_SIGN_COMMON);
				for (String billId : billIds) {
					batchpaylist.add(new BatchPaymentRelation(
							new BatchPaymentRelationId(Long.valueOf(billId
									.trim()), paymentBatch.getId()),false,false));
				}
				billIdsList.addAll(Arrays.asList(billIds));
			}
			agentDAO.batchSaveVO(batchpaylist);
			agentDAO.updateBillStatusByIds(billIdsList,3);
			isCreate = true;
		}
		return isCreate;
	}

	@Override
	public List<CountReportBo> findReportByAgentId(Long id, String year,
			String areaId) throws RuntimeException {
		// TODO Auto-generated method stub
		List userlist = agentDAO.findUserByParam(areaId, year);
		Map<String,Object> monthMap = getUserNum(year,userlist);
		return this.getReports(id, year, monthMap);
	}
	@Override
	public List findAgentSignRateByAgentId(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.findAgentSignRateByAgentId(id);
	}
	@Override
	public void updateObject(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		agentDAO.update(obj);
	}
	@Override
	public void updateSignRateById(Long id, Double rate)
			throws RuntimeException {
		// TODO Auto-generated method stub
		agentDAO.updateSignRateById(id, rate);
	}
	@Override
	public void executeBatchBusiness(final Long batchId, List<String> list,
			Integer status) throws RuntimeException {
		// TODO Auto-generated method stub
		agentDAO.updateBillStatusByIds(list,status);
		agentDAO.updateComplateFlagByBatchId(batchId, list,false);
		Long num = agentDAO.findBillTotalBatchId(batchId, new HashMap<String,Object>(){
		     {
		    	 put("batchId", batchId);
		    	 put("paymentStatus",3);
		     }
		     });
		if(num!=null&&num==0){
		   agentDAO.updateStatusByBatchId(batchId, 1);
		}
	}
	@Override
	public List getNoticeByBillId(Long batchId,Long billId,Integer doflag) throws RuntimeException {
		// TODO Auto-generated method stub
		if(doflag==0)
		   agentDAO.updateErrHandFlagById(batchId,billId,true);
		return agentDAO.findNoticeByBillId(billId);
		
	}
	@Override
	public List getOutBatchBill(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.getOutBatchBill(Qparam,page,rows);
	}
	@Override
	public List statislSummaryErrorBill(Map<String, Object> Qparam,
			Integer page, Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.statislSummaryErrorBill(Qparam,page,rows);
	}
	@Override
	public List statislSummaryOutNoCompBill(Map<String, Object> Qparam,
			Integer page, Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.statislSummaryOutNoCompBill(Qparam, page, rows);
	}
	@Override
	public Long statislSummaryErrorBillCount(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.statislSummaryErrorBillCount(Qparam);
	}
	@Override
	public Long statislSummaryOutBillCount(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.statislSummaryOutBillCount(Qparam);
	}
	@Override
	public Long getCountOutBatchBill(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return agentDAO.getCountOutBatchBill(Qparam);
	}

}
