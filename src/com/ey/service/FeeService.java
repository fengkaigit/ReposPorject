package com.ey.service;

import java.util.Date;

import com.ey.dao.entity.FeeRule;

public interface FeeService {
	public FeeRule getFeeRule(int payType,Date date)throws RuntimeException;
}
