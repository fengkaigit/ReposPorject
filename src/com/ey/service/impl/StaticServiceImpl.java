package com.ey.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.bo.StaticDataBO;
import com.ey.dao.StaticDAO;
import com.ey.dao.entity.BaseCustomProp;
import com.ey.dao.entity.BaseCustomValue;
import com.ey.service.StaticService;
import com.ey.util.StringUtil;

@Service("staticService")
public class StaticServiceImpl implements StaticService {
	@Autowired
	StaticDAO staticDAO;

	@Override
	public List<StaticDataBO> findStatics(String typeCode) {
		List<StaticDataBO> list = new ArrayList();
		if (StringUtil.isEmptyString(typeCode)) {
			List<BaseCustomProp> staticDataList = staticDAO.listProps(null);
			List<BaseCustomValue> paramsList = staticDAO.listValues(null);
			for (BaseCustomProp sdst : staticDataList) {
				String tc = sdst.getPropEngName();
				List<BaseCustomValue> lps = new ArrayList();
				for (BaseCustomValue pst : paramsList) {
					if (pst.getId().getCustomEngName().equals(tc)) {
						lps.add(pst);
					}
				}
				StaticDataBO sdb = new StaticDataBO(sdst.getPropEngName(), sdst
						.getPropChName(), lps);
				list.add(sdb);
			}
		} else {
			BaseCustomProp sdv = (BaseCustomProp) staticDAO.get(
					BaseCustomProp.class, typeCode);
			List<BaseCustomValue> paramsList = staticDAO.listValues(typeCode);
			StaticDataBO sdb = new StaticDataBO(sdv.getPropEngName(), sdv
					.getPropChName(), paramsList);
			list.add(sdb);
		}
		return list;
	}

	@Override
	public List<BaseCustomValue> listValues(String typeCode) {
		return staticDAO.listValues(typeCode);
	}

}
