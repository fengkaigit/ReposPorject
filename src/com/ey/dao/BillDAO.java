package com.ey.dao;

import java.util.Date;
import java.util.List;

import com.ey.dao.base.BaseDAO;

import com.ey.bo.PaymentBillBo;
import com.ey.bo.ServiceChargeBillBo;
import com.ey.bo.SettleBillBo;

public interface BillDAO extends BaseDAO {

	public List<ServiceChargeBillBo> getServiceBillList(Date startDate, Date endDate) throws RuntimeException;
	
	public List<PaymentBillBo> getPaymentBillList(Long serviceBillId) throws RuntimeException;
	
	public List<ServiceChargeBillBo> getPoundageBillList() throws RuntimeException;
	
	public List<ServiceChargeBillBo> getServiceBillList(Long poundageBillId, String tableName, String relationName, String colName) throws RuntimeException;
	
	public List<ServiceChargeBillBo> getProfitBillList() throws RuntimeException;
	
	public List<ServiceChargeBillBo> getIncomeBillList() throws RuntimeException;
	
	public List<SettleBillBo> getSettleBillList() throws RuntimeException;
	
}
