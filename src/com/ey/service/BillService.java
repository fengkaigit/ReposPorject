package com.ey.service;

import java.util.List;

import com.ey.bo.PaymentBillBo;
import com.ey.bo.ServiceChargeBillBo;


public interface BillService {
	
	public List<ServiceChargeBillBo> getServiceBillList() throws RuntimeException;
	
	public List<PaymentBillBo> getPaymentBillList(Long serviceBillId) throws RuntimeException;
	
}
