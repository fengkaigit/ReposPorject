package com.ey.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ey.bo.StaticDataBO;
import com.ey.dao.entity.BankInfo;
import com.ey.dao.entity.BaseCustomValue;

public interface StaticService {
	public List<StaticDataBO> findStatics(String typeCode) throws RuntimeException;
	public List<BaseCustomValue> listValues(String typeCode) throws RuntimeException;
	List<BankInfo> listBanks() throws RuntimeException;
	Object saveObject(Object obj) throws RuntimeException;
	void updateObject(Object obj) throws RuntimeException;
	public String getLabel(List<BaseCustomValue> list, Integer id) throws RuntimeException;
	List findCustomProps(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
	Long getTotalCustomProp(Map<String,Object> Qparam) throws RuntimeException;
	List findCustomValues(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
	Long getTotalCustomValue(Map<String,Object> Qparam) throws RuntimeException;
	Object getObject(Class classz,Serializable id) throws RuntimeException;
	void deleteCustomPropByIds(String[] ids) throws RuntimeException;
	void deleteCustomValueByIds(Object[][] ids) throws RuntimeException;
}
