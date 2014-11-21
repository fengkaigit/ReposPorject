package com.ey.service;

import java.util.List;
import java.util.UUID;

import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.TransferRecords;

public interface ProfitCalculateService {
	
	public Integer changePaymentBillStatus();
	
	public List<PaymentBill> findPaymentBillList(Integer paymentStatus,Integer divideStatus);
	
	public void updatePaymentBillUUID(UUID uuid) throws Exception;
	
	public void saveTempPaymentBillTask(PaymentBill paymentBill,UUID uuid)throws Exception;
	
	public Double createServiceChargeBill(Long serviceBillId) throws Exception;
	
	public void saveServicePaymentRelation(Long serviceBillId, List<PaymentBill> billList) throws Exception;
	
	public void createTransferBill(Long serviceTransferBillId, Double profitMoney, Long outAccountId, Long inAccountId) throws Exception;
	
	public void saveServiceTransferRecords(Long serviceBillId, Long transferAccountId) throws Exception;
	
	public void clearTempPaymentBill() throws Exception;
	
	public Double findTransferRecordsPoundage(Long serviceBillId) throws Exception;
	
	public Double findServiceBillMoney(Long serviceBillId) throws Exception;
	
	public void saveProfitBill(Long profitBillId,Double profitMoney) throws Exception;
	
	public void saveServiceProfitRelation(Long serviceBillId, Long profitBillId) throws Exception;
	
	public Long getSystemAccountId(Integer accountType) throws Exception;
	
	public void saveProfitTransferRecords(Long profitBillId, Long transferAccountId) throws Exception;
	
	public List<PaymentBill> findPaymentBillList(Long serviceBillId) throws Exception;
	
	public List<AgentInfo> findAgentInfo(List<PaymentBill> paymentList) throws Exception;

}
