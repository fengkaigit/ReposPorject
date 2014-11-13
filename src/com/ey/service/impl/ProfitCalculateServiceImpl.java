package com.ey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dao.ProfitCalculateDAO;
import com.ey.dao.entity.PaymentBill;
import com.ey.service.ProfitCalculateService;

@Service("ProfitCalculateService")
//@Transactional(propagation = Propagation.REQUIRED, readOnly = false,rollbackFor = RuntimeException.class)
public class ProfitCalculateServiceImpl implements ProfitCalculateService {

	@Autowired
	private ProfitCalculateDAO profitDao;

	@Override
	public List<PaymentBill> findPaymentBillList() {
		// TODO Auto-generated method stub
		List<PaymentBill> lst = null; 
		Integer status = profitDao.updateStatus("PaymentBill", "paymentStatus", "divideStatus", 10, 1);
		
		if (status == 0){
			lst = profitDao.findPaymentBills();
		}
		return lst;
	}

}
