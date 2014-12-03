package com.ey.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.dao.CatvDAO;
import com.ey.dao.entity.CatvInfo;
import com.ey.dao.base.impl.BaseDAOImpl;

@Repository("catvDAO")
public class CatvDAOImpl extends BaseDAOImpl implements CatvDAO {

	@Override
	public List<CatvInfo> getCatvInfo(String areaId, int stationType)
			throws RuntimeException {
		String hql = "from CatvInfo where areaId=? and televisionType=?";
		return this.find(hql, new Object[]{areaId,stationType});
	}

}
