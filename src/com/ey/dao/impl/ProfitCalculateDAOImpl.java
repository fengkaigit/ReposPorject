package com.ey.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.dao.ProfitCalculateDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.TempPaymentBill;

@Repository("ProfitCalculateDAO") 
public class ProfitCalculateDAOImpl extends BaseDAOImpl implements ProfitCalculateDAO{

	@Override
	public void update(PaymentBill paymentBill) throws RuntimeException {
		super.update(paymentBill);
	}

	@Override
	public void save(TempPaymentBill paymentBill) throws RuntimeException {
		super.save(paymentBill);
	}

	@Override
	public Integer updateStatus(String tableName, String columnName1, String columnName2, Integer status1, Integer status2)
			throws RuntimeException {
		String hql = "update "+ tableName +" set "+ columnName2 +" =? where "+ columnName1 + "=?"; 
		Integer status = this.executeHql(hql, new Object[]{status2,status1});
		return status;
	}

	@Override
	public List<PaymentBill> findPaymentBills()
			throws RuntimeException {
		String hql = "from PaymentBill where paymentStatus=? and divideStatus=?";
		List<PaymentBill> payment = this.find(hql, new Object[]{10,1});
		return payment;
	}
	
	

}
