package com.ey.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ey.dao.entity.ServiceChargeBill;
import com.ey.service.BillService;

@Service("billService")
public class BillServiceImpl implements BillService {

	@Override
	public List<ServiceChargeBill> getServiceBillList() throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

}
