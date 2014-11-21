package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;

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
}
