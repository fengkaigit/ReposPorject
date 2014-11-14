package com.ey.service;

import java.util.List;
import java.util.UUID;

import com.ey.dao.entity.PaymentBill;

public interface ProfitCalculateService {
	
	public Integer changePaymentBillStatus();
	
	public List<PaymentBill> findPaymentBillList(Integer paymentStatus,Integer divideStatus);
	
	public void updatePaymentBillUUID(UUID uuid) throws Exception;
	
	public void saveTempPaymentBillTask(PaymentBill paymentBill,UUID uuid)throws Exception;
	
	public Double createServiceChargeBill(Long serviceBillId) throws Exception;
	
	public void saveServicePaymentRelation(Long serviceBillId, List<PaymentBill> billList) throws Exception;
	
	public void createServiceTransferBill(Long serviceTransferBillId, Double profitMoney) throws Exception;
	
	public void saveServiceTransferRecords(Long serviceBillId, Long transferAccountId) throws Exception;
	
	public void clearTempPaymentBill() throws Exception;

}
