package com.ey.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dao.AreaDAO;
import com.ey.dao.entity.Area;
import com.ey.service.AreaService;
import com.ey.util.StringUtil;


@Service("areaService")
public class AreaServiceImpl implements AreaService {

	@Autowired
    private AreaDAO areaDAO;
	
	@Override
	public void saveArea(Area area) throws RuntimeException {
		// TODO Auto-generated method stub
		areaDAO.saveOrUpdate(area);
	}

	@Override
	public void deleteByAreaIds(String[] ids) throws RuntimeException {
		// TODO Auto-generated method stub
		 for(String id:ids){
         	if(StringUtil.isEmptyString(id))
         		continue;
         	areaDAO.deleteByAreaId(id);
         }
	}

	@Override
	public List<Area> getAreas(Map<String, Object> Qparam, Integer page,
			Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		return areaDAO.getAreas(Qparam, page, rows);
	}

	@Override
	public Area getArea(String id) throws RuntimeException {
		// TODO Auto-generated method stub
		return (Area)areaDAO.get(Area.class, id);
	}

	@Override
	public List<Area> getAreasByCity(String cityId) throws RuntimeException{
		// TODO Auto-generated method stub
		return areaDAO.getAreasByCity(cityId);
	}
}
