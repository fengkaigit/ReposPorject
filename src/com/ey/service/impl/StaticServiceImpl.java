package com.ey.service.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.bo.StaticDataBO;
import com.ey.dao.StaticDAO;
import com.ey.dao.common.dbid.DbidGenerator;
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

	@Override
	public void updateObject(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		 staticDAO.update(obj);
	}

	@Override
	public List findCustomProps(Map<String, Object> Qparam, Integer page,
			Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		return staticDAO.findCustomProps(Qparam, page, rows);
	}

	@Override
	public List findCustomValues(Map<String, Object> Qparam, Integer page,
			Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		return staticDAO.findCustomValues(Qparam, page, rows);
	}

	@Override
	public Long getTotalCustomProp(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return staticDAO.getTotalCustomProp(Qparam);
	}

	@Override
	public Long getTotalCustomValue(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return staticDAO.getTotalCustomValue(Qparam);
	}

	@Override
	public Object getObject(Class classz, Serializable id)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return staticDAO.get(classz, id);
	}

	@Override
	public void deleteCustomPropByIds(String[] ids) throws RuntimeException {
		// TODO Auto-generated method stub
		for(String customPropName:ids){
		    staticDAO.deleteCustomProp(customPropName);
		}
	}

	@Override
	public void deleteCustomValueByIds(Object[][] ids) throws RuntimeException {
		// TODO Auto-generated method stub
		for(Object[] o:ids){
			staticDAO.deleteCustomValue(o[0]+"", Integer.valueOf(o[1]+""));
		}
	}

	@Override
	public void saveOrUpdateObject(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		staticDAO.saveOrUpdate(obj);
	}

	@Override
	public List findTransferRate(Map<String, Object> Qparam, Integer page,
			Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		return staticDAO.findTransferRate(Qparam, page, rows);
	}

	@Override
	public Long getTotalTransferRate(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return staticDAO.getTotalTransferRate(Qparam);
	}

	@Override
	public void deleteObject(Object obj) throws RuntimeException {
		// TODO Auto-generated method stub
		staticDAO.delete(obj);
	}

	@Override
	public Object saveObject(Object obj, Boolean createDbId)
			throws RuntimeException {
		// TODO Auto-generated method stub
		if(createDbId){
			staticDAO.getDbId(obj);
		}
		staticDAO.save(obj);
		return null;
	}

	@Override
	public List findNoticeInfos(Map<String, Object> Qparam, Integer page,
			Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		return staticDAO.findNoticeInfos(Qparam, page, rows);
	}

	@Override
	public Long getTotalNoticeInfo(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return staticDAO.getTotalNoticeInfo(Qparam);
	}
}
