package com.ey.service;

import java.util.List;

import com.ey.dao.entity.PaymentBill;

public interface ProfitCalculateService {
	
	public List<PaymentBill> findPaymentBillList();

}
