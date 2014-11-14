package com.ey.dao;

import java.util.List;
import java.util.UUID;

import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.TempPaymentBill;

public interface ProfitCalculateDAO extends BaseDAO {

	public void update(PaymentBill paymentBill) throws RuntimeException;
	
	public void saveTempPaymentBill(TempPaymentBill paymentBill) throws RuntimeException;
	
	public Integer updateStatus(String tableName, String columnName1, String columnName2, Integer status1, Integer status2) throws RuntimeException;
	
	public List<PaymentBill> findPaymentBills(Integer paymentStatus,Integer divideStatus) throws RuntimeException;
	
	public Double getProfitMoney() throws RuntimeException;
	
	public void updatePaymentBillUUID(UUID uuid) throws RuntimeException;
	
	public Long getAccountId(Integer acctType) throws RuntimeException;
	
	public void deleteTempPaymentBill() throws RuntimeException;
	
}
