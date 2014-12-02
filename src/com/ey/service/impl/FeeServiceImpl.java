package com.ey.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dao.FeeDAO;
import com.ey.dao.entity.FeeRule;
import com.ey.service.FeeService;
@Service("feeService")
public class FeeServiceImpl implements FeeService {
	@Autowired
	private FeeDAO feeDAO;
	@Override
	public FeeRule getFeeRule(int payType, Date date) throws RuntimeException{
		return feeDAO.getFeeRule(payType,date);
	}

}
