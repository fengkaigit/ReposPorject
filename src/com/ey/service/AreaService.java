package com.ey.service;

import java.util.List;
import java.util.Map;

import com.ey.dao.entity.Area;


public interface AreaService {
    
	void saveArea(Area area) throws RuntimeException;
	
	void deleteByAreaIds(String[] ids) throws RuntimeException;
	
    List<Area> getAreas(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
    
    Area getArea(String id) throws RuntimeException;

}
