package com.ey.dao.impl;
import java.util.List;
import java.util.UUID;

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
	public void saveTempPaymentBill(TempPaymentBill paymentBill) throws RuntimeException {
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
	public List<PaymentBill> findPaymentBills(Integer paymentStatus,Integer divideStatus)
			throws RuntimeException {
		String hql = "from PaymentBill where paymentStatus=? and divideStatus=? and uuid is null";
		List<PaymentBill> payment = this.find(hql, new Object[]{paymentStatus,divideStatus});
		return payment;
	}

	@Override
	public Double getProfitMoney() throws RuntimeException {
		String hql="select sum(poundage) from TempPaymentBill where stageStatus=?";
		List<Double> money = this.find(hql,new Object[]{1});
		if (money!=null && money.size()>0)
			return money.get(0);
		else
			return 0d;
	}

	@Override
	public void updatePaymentBillUUID(UUID uuid) throws RuntimeException {
		String hql = "update PaymentBill set uuid=? where divideStatus=? and paymentStatus=? and uuid is null";
		this.executeHql(hql, new Object[]{uuid.toString(),1,10});
	}

	@Override
	public Long getAccountId(Integer acctType) throws RuntimeException {
		String hql = "select bankAccountId from SystemAccount where acctType=?";
		List<Long> accountId = this.find(hql,new Object[]{acctType});
		if (accountId!=null && accountId.size()>0)
			return accountId.get(0);
		else
			return null;
	}

	@Override
	public void deleteTempPaymentBill() throws RuntimeException {
		String hql = "delete from TempPaymentBill";
		this.executeHql(hql);
	}
	
	

}
