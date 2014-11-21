package com.ey.dao;

import java.util.List;
import java.util.Map;

import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.Area;

public interface AreaDAO extends BaseDAO {
	
	void deleteByAreaId(String id) throws RuntimeException;
	
    List<Area> getAreas(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
    
	List<Area> getAreasByCity(String cityId) throws RuntimeException;

}
