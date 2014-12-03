package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.bo.AreaBo;
import com.ey.dao.AreaDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.Area;


@Repository("areaDAO")
public class AreaDAOImpl extends BaseDAOImpl implements AreaDAO {

	@Override
	public void deleteByAreaId(String id) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "delete from Area where id = ?";
        this.executeHql(hql, new Object[]{id});
	}

	@Override
	public List<Area> getAreas(Map<String, Object> Qparam,
			Integer page, Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		//List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("from Area a where 1=1");
		return this.find(hql.toString(), page, rows);
	}
	public List<Area> getAreasByCity(String cityId) {
		String hql = "from Area where city=? order by id";
		return this.find(hql, new Object[]{cityId});
	}

	@Override
	public List<Area> getAreaList(String areaType)
			throws RuntimeException {
		String hql = "from Area where areaType=100 ";
		List<Object> params = new ArrayList();
		if (areaType.substring(2).equals("1")){
			hql = hql + " or areaType=?";
			params.add(1);
		}
		if (areaType.substring(1,2).equals("1")){
			hql = hql + " or areaType=?";
			params.add(10);
		}
		if (areaType.substring(0,1).equals("1")){
			hql = hql + " or areaType=?";
			params.add(100);
		}
		hql = hql + "order by id";
		return this.find(hql,params.toArray());
	}
}
