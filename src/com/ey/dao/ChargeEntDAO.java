package com.ey.dao;

import java.util.List;
import java.util.Map;

import com.ey.bo.ChargeEntBo;
import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.ChargeEnterprise;

public interface ChargeEntDAO extends BaseDAO{
	
	void deleteByEntId(Long id) throws RuntimeException;
	
    List<ChargeEntBo> getAllChargeEnt(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
    
    ChargeEntBo getChargeEnt(Long id) throws RuntimeException;

	List<ChargeEntBo> getChargesByArea(String areaId);

	List<ChargeEnterprise> getChargesByArea(String areaId, int payType);
	
	BankAccount getBankAccount(Long id) throws RuntimeException;
    Long getTotalByParam(Map<String,Object> Qparam) throws RuntimeException;

}
