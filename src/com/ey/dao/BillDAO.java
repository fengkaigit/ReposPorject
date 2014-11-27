package com.ey.dao;

import java.util.List;

import com.ey.dao.base.BaseDAO;

import com.ey.bo.PaymentBillBo;
import com.ey.bo.ServiceChargeBillBo;

public interface BillDAO extends BaseDAO {

	public List<ServiceChargeBillBo> getServiceBillList() throws RuntimeException;
	
	public List<PaymentBillBo> getPaymentBillList(Long serviceBillId) throws RuntimeException;
	
}
