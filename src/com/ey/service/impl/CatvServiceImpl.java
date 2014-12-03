package com.ey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dao.CatvDAO;
import com.ey.dao.entity.CatvInfo;
import com.ey.service.CatvService;

@Service("catvService")
public class CatvServiceImpl implements CatvService {

	@Autowired
    private CatvDAO catvDAO;
	
	@Override
	public List<CatvInfo> getCatvInfo(String areaId, int stationType)
			throws RuntimeException {

		return null;
	}

}
