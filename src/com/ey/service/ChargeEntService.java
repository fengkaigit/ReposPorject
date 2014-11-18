package com.ey.service;

import java.util.List;
import java.util.Map;

import com.ey.bo.ChargeEntBo;
import com.ey.dao.entity.ChargeEnterprise;

public interface ChargeEntService {
    
	
	void saveChargeEnt(ChargeEnterprise chargeEnt) throws RuntimeException;
    
    void deleteChargeEntByIds(String[] ids) throws RuntimeException;
    
    List<ChargeEntBo> getAllChargeEnt(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
    
    ChargeEntBo getChargeEnt(Long id) throws RuntimeException;
}
