package com.ey.service;

import java.util.List;

import com.ey.dao.entity.ServiceChargeBill;

public interface BillService {
	
	public List<ServiceChargeBill> getServiceBillList() throws RuntimeException;
}
