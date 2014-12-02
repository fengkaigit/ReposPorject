package com.ey.service;

import java.util.List;

import com.ey.bo.StaticDataBO;
import com.ey.dao.entity.BankInfo;
import com.ey.dao.entity.BaseCustomValue;

public interface StaticService {
	public List<StaticDataBO> findStatics(String typeCode) throws RuntimeException;
	public List<BaseCustomValue> listValues(String typeCode) throws RuntimeException;
	List<BankInfo> listBanks() throws RuntimeException;
	Object saveObject(Object obj) throws RuntimeException;
}
