package com.ey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.bo.PaymentBillBo;
import com.ey.bo.ServiceChargeBillBo;
import com.ey.dao.BillDAO;
import com.ey.service.BillService;

@Service("billService")
public class BillServiceImpl implements BillService {

	@Autowired
    private BillDAO billDao;
	
	@Override
	public List<ServiceChargeBillBo> getServiceBillList() throws RuntimeException {
		return billDao.getServiceBillList();
	}

	@Override
	public List<PaymentBillBo> getPaymentBillList(Long serviceBillId)
			throws RuntimeException {
		return billDao.getPaymentBillList(serviceBillId);
	}

}
