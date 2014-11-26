package com.ey.dao;

import java.util.List;

import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.BankInfo;
import com.ey.dao.entity.BaseCustomProp;
import com.ey.dao.entity.BaseCustomValue;

public interface StaticDAO extends BaseDAO {

	List<BaseCustomValue> listValues(String typeCode);

	List<BaseCustomProp> listProps(String typeCode);
	
	List<BankInfo> listBanks() throws RuntimeException;

}
