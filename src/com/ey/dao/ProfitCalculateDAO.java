package com.ey.dao;

import java.util.List;
import java.util.UUID;

import com.ey.bo.AgentBo;
import com.ey.bo.PaymentAgentBo;
import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.SystemClearStatis;
import com.ey.dao.entity.TempPaymentBill;
import com.ey.dao.entity.TransferRecords;
import com.ey.dao.entity.AgentClearStatis;

public interface ProfitCalculateDAO extends BaseDAO {

	public void update(PaymentBill paymentBill) throws RuntimeException;
	
	public void saveTempPaymentBill(TempPaymentBill paymentBill) throws RuntimeException;
	
	public Integer updateStatus(String tableName, String columnName1, String columnName2, Integer status1, Integer status2, String strWhere) throws RuntimeException;
	
	public List<PaymentBill> findPaymentBills(Integer paymentStatus,Integer divideStatus) throws RuntimeException;
	
	public List<PaymentBill> findPaymentBills(Long serviceBillId) throws RuntimeException;
	
	public Double getProfitMoney() throws RuntimeException;
	
	public void updatePaymentBillUUID(UUID uuid) throws RuntimeException;
	
	public Long getAccountId(Integer acctType) throws RuntimeException;
	
	public void deleteTempPaymentBill() throws RuntimeException;
	
	public Double findTransferRecordsPoundage(Long serviceBillId) throws RuntimeException;
	
	public List<PaymentAgentBo> findAgentInfo(Long serviceBillId) throws RuntimeException;
	
	public Double findServiceBillMoney(Long serviceBillId) throws RuntimeException;
	
	public AgentBo findAgentRule(Long agentId) throws RuntimeException;
	
	public Double getSystemProfitMoney(Long serviceBillId) throws RuntimeException;
	
	public Double getSystemSettleMoney(Long serviceBillId) throws RuntimeException;
	
	public Integer saveHedgeDetail(int status1,int status2) throws RuntimeException;
	
	public Double getSteriliseBillMoney(int status) throws RuntimeException;
	
	public List<Long> getHedgeList(int status) throws RuntimeException;

	public List<AgentClearStatis> getAgentClearBill(Integer year, Integer month) throws RuntimeException;

	public SystemClearStatis getSystemClearBill(Integer year, Integer month) throws RuntimeException;
	
	public Long getBillId(String billName) throws RuntimeException;
	
}
