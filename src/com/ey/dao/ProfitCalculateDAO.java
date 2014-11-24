package com.ey.dao;

import java.util.List;
import java.util.UUID;

import com.ey.bo.AgentBo;
import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.TempPaymentBill;
import com.ey.dao.entity.TransferRecords;

public interface ProfitCalculateDAO extends BaseDAO {

	public void update(PaymentBill paymentBill) throws RuntimeException;
	
	public void saveTempPaymentBill(TempPaymentBill paymentBill) throws RuntimeException;
	
	public Integer updateStatus(String tableName, String columnName1, String columnName2, Integer status1, Integer status2) throws RuntimeException;
	
	public List<PaymentBill> findPaymentBills(Integer paymentStatus,Integer divideStatus) throws RuntimeException;
	
	public List<PaymentBill> findPaymentBills(Long serviceBillId) throws RuntimeException;
	
	public Double getProfitMoney() throws RuntimeException;
	
	public void updatePaymentBillUUID(UUID uuid) throws RuntimeException;
	
	public Long getAccountId(Integer acctType) throws RuntimeException;
	
	public void deleteTempPaymentBill() throws RuntimeException;
	
	public Double findTransferRecordsPoundage(Long serviceBillId) throws RuntimeException;
	
	public List<AgentInfo> findAgentInfo(Long serviceBillId) throws RuntimeException;
	
	public Double findServiceBillMoney(Long serviceBillId) throws RuntimeException;
	
	public AgentBo findAgentRule(Long agentId) throws RuntimeException;
	
}
