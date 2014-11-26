package com.ey.service;

import java.util.List;

import com.ey.bo.StaticDataBO;
import com.ey.dao.entity.BankInfo;
import com.ey.dao.entity.BaseCustomValue;

public interface StaticService {
	public List<StaticDataBO> findStatics(String typeCode);
	public List<BaseCustomValue> listValues(String typeCode);
	List<BankInfo> listBanks() throws RuntimeException;
	Object saveObject(Object obj) throws RuntimeException;
}
