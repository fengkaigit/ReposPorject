package com.ey.dao;

import java.util.List;

import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.CatvInfo;

public interface CatvDAO extends BaseDAO {

	List<CatvInfo> getCatvInfo(String areaId, int stationType) throws RuntimeException;
}
