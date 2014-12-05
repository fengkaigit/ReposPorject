package com.ey.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.bo.StaticDataBO;
import com.ey.dao.StaticDAO;
import com.ey.dao.entity.BankInfo;
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

	@Override
	public List<BankInfo> listBanks() throws RuntimeException {
		// TODO Auto-generated method stub
		return staticDAO.listBanks();
	}

	@Override
	public Object saveObject(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		return staticDAO.save(obj);
	}
	public String getLabel(List<BaseCustomValue> list, Integer id) {
		String label = null;
		for (BaseCustomValue st : list) {
			if (st.getId().getDataValue().intValue() == id.intValue()) {
				label = st.getPropChName();
				break;
			}
		}
		return label;
	}
}
