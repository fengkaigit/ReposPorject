package com.ey.service;

import java.util.List;

import com.ey.dao.entity.CatvInfo;

public interface CatvService {

	List<CatvInfo> getCatvInfo(String areaId,int stationType) throws RuntimeException;
	
}
