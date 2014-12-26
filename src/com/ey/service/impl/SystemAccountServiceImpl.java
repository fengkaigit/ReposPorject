package com.ey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.bo.SystemAccountBo;
import com.ey.dao.SystemAccountDAO;
import com.ey.dao.entity.SystemAccount;
import com.ey.service.SystemAccountService;

@Service("sysService")  
public class SystemAccountServiceImpl implements SystemAccountService {

	@Autowired
    private SystemAccountDAO systemAccountDAO;
	
	@Override
	public List<SystemAccountBo> findSystemAccountList() throws RuntimeException {
		// TODO Auto-generated method stub
		return systemAccountDAO.findSystemAccountList();
	}

	@Override
	public SystemAccountBo getSysAccount(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		return systemAccountDAO.getSysAccount(id);
	}

}
