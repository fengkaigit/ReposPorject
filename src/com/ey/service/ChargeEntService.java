package com.ey.service;

import java.util.List;
import java.util.Map;

import com.ey.bo.ChargeEntBo;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.ChargeEnterprise;

public interface ChargeEntService {
    
	
	void saveChargeEnt(ChargeEnterprise chargeEnt,BankAccount bankAccount) throws RuntimeException;
	
	void updateChargeEnt(ChargeEnterprise chargeEnt,BankAccount bankAccount) throws RuntimeException;

    void deleteChargeEntByIds(String[] ids) throws RuntimeException;
    
    List<ChargeEntBo> getAllChargeEnt(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
    
    Long getTotalByParam(Map<String,Object> Qparam) throws RuntimeException;
    
    ChargeEntBo getChargeEnt(Long id) throws RuntimeException;
               
    ChargeEnterprise getChargeEnterprise(Long id) throws RuntimeException;
    List<ChargeEntBo> getChargesByArea(String areaId) throws RuntimeException;;
    List<ChargeEnterprise> getChargesByArea(String areaId,int payType) throws RuntimeException;
	BankAccount getBankAccount(Long id) throws RuntimeException;

}
