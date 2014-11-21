package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.BaseCustomProp;
import com.ey.dao.entity.BaseCustomValue;
import com.ey.util.StringUtil;
import com.ey.dao.StaticDAO;
@Repository("staticDAO")
public class StaticDAOImpl extends BaseDAOImpl implements StaticDAO {

	@Override
	public java.util.List<BaseCustomProp> listProps(String typeCode) {
		StringBuffer buffer = new StringBuffer("from BaseCustomProp");
		List<Object> params = new ArrayList();
		if(!StringUtil.isEmptyString(typeCode)){
			buffer.append(" where propEngName=?");
			params.add(typeCode);
		}
		return this.find(buffer.toString(), params);
	}

	@Override
	public java.util.List<BaseCustomValue> listValues(String typeCode) {
		StringBuffer buffer = new StringBuffer("from BaseCustomValue");
		List<Object> params = new ArrayList();
		if(!StringUtil.isEmptyString(typeCode)){
			buffer.append(" where id.customEngName=?");
			params.add(typeCode);
		}
		buffer.append(" order by id.customEngName,id.dataValue");
		return this.find(buffer.toString(), params);
	}

}
