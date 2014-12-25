package com.ey.dao;

import java.util.List;
import java.util.Map;

import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.BankInfo;
import com.ey.dao.entity.BaseCustomProp;
import com.ey.dao.entity.BaseCustomValue;

public interface StaticDAO extends BaseDAO {

	List<BaseCustomValue> listValues(String typeCode);

	List<BaseCustomProp> listProps(String typeCode);
	
	List<BankInfo> listBanks() throws RuntimeException;
	
	List findCustomProps(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
	Long getTotalCustomProp(Map<String,Object> Qparam) throws RuntimeException;
	List findCustomValues(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
	Long getTotalCustomValue(Map<String,Object> Qparam) throws RuntimeException;
    void deleteCustomProp(String customPropName)  throws RuntimeException;
    void deleteCustomValue(String customPropName,Integer customDataValue)  throws RuntimeException;
    List findTransferRate(Map<String,Object> Qparam,Integer page,Integer rows)throws RuntimeException;
	Long getTotalTransferRate(Map<String,Object> Qparam) throws RuntimeException;
    
}
