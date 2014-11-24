package com.ey.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.PaymentBill;

public interface ProfitCalculateService {
	
	public Integer changePaymentBillStatus();
	
	public List<PaymentBill> findPaymentBillList(Integer paymentStatus,Integer divideStatus) throws RuntimeException;
	
	public void updatePaymentBillUUID(UUID uuid) throws RuntimeException;
	
	public void saveTempPaymentBillTask(PaymentBill paymentBill,UUID uuid)throws RuntimeException, IllegalAccessException, InvocationTargetException;
	
	public Double createServiceChargeBill(Long serviceBillId) throws RuntimeException;
	
	public void saveServicePaymentRelation(Long serviceBillId, List<PaymentBill> billList) throws RuntimeException;
	
	public void createTransferBill(Long serviceTransferBillId, Double profitMoney, Long outAccountId, Long inAccountId) throws RuntimeException;
	
	public void saveServiceTransferRecords(Long serviceBillId, Long transferAccountId) throws RuntimeException;
	
	public void clearTempPaymentBill() throws RuntimeException;
	
	public Double findTransferRecordsPoundage(Long serviceBillId) throws RuntimeException;
	
	public Double findServiceBillMoney(Long serviceBillId) throws RuntimeException;
	
	public void saveProfitBill(Long profitBillId,Double profitMoney) throws RuntimeException;
	
	public void saveServiceProfitRelation(Long serviceBillId, Long profitBillId) throws RuntimeException;
	
	public Long getSystemAccountId(Integer accountType) throws RuntimeException;
	
	public void saveProfitTransferRecords(Long profitBillId, Long transferAccountId) throws RuntimeException;
	
	public List<PaymentBill> findPaymentBillList(Long serviceBillId) throws RuntimeException;
	
	public List<AgentInfo> findAgentInfo(List<PaymentBill> paymentList) throws RuntimeException;

}
