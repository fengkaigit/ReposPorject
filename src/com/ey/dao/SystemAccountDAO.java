package com.ey.dao;

import java.util.List;

import com.ey.bo.SystemAccountBo;
import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.SystemAccount;

public interface SystemAccountDAO extends BaseDAO {

	public List<SystemAccountBo> findSystemAccountList() throws RuntimeException;
	
	public SystemAccountBo getSysAccount(Long id) throws RuntimeException;
}
