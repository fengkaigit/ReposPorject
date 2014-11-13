package com.ey.dao;

import java.util.List;

import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.TempPaymentBill;

public interface ProfitCalculateDAO extends BaseDAO {

	public void update(PaymentBill paymentBill) throws RuntimeException;
	
	public void save(TempPaymentBill paymentBill) throws RuntimeException;
	
	public Integer updateStatus(String tableName, String columnName1, String columnName2, Integer status1, Integer status2) throws RuntimeException;
	
	public List<PaymentBill> findPaymentBills() throws RuntimeException;
}
