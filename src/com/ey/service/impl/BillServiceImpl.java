package com.ey.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.bo.PaymentBillBo;
import com.ey.bo.ServiceChargeBillBo;
import com.ey.bo.SettleBillBo;
import com.ey.dao.BillDAO;
import com.ey.service.BillService;

@Service("billService")
public class BillServiceImpl implements BillService {

	@Autowired
    private BillDAO billDao;
	
	@Override
	public List<ServiceChargeBillBo> getServiceBillList(Date startDate, Date endDate) throws RuntimeException {
		return billDao.getServiceBillList(startDate, endDate);
	}

	@Override
	public List<PaymentBillBo> getPaymentBillList(Long serviceBillId)
			throws RuntimeException {
		return billDao.getPaymentBillList(serviceBillId);
	}

	@Override
	public List<ServiceChargeBillBo> getPoundageBillList()
			throws RuntimeException {
		return billDao.getPoundageBillList();
	}

	@Override
	public List<ServiceChargeBillBo> getServiceBillList(Long poundageBillId, String tableName, String relationName, String colName)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return billDao.getServiceBillList(poundageBillId,tableName,relationName,colName);
	}
	
	@Override
	public List<ServiceChargeBillBo> getProfitBillList()
			throws RuntimeException {
		return billDao.getProfitBillList();
	}
	
	@Override
	public List<ServiceChargeBillBo> getIncomeBillList()
			throws RuntimeException {
		return billDao.getIncomeBillList();
	}

	@Override
	public List<SettleBillBo> getSettleBillList() throws RuntimeException {
		// TODO Auto-generated method stub
		return billDao.getSettleBillList();
	}

}
