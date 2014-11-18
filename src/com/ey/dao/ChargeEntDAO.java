package com.ey.dao;

import java.util.List;
import java.util.Map;

import com.ey.bo.ChargeEntBo;
import com.ey.dao.base.BaseDAO;

public interface ChargeEntDAO extends BaseDAO{
	
	void deleteByEntId(Long id) throws RuntimeException;
	
    List<ChargeEntBo> getAllChargeEnt(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
    
    ChargeEntBo getChargeEnt(Long id) throws RuntimeException;

}
