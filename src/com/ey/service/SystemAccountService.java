package com.ey.service;

import java.util.List;

import com.ey.bo.SystemAccountBo;
import com.ey.dao.entity.SystemAccount;

public interface SystemAccountService {

	public List<SystemAccountBo> findSystemAccountList() throws RuntimeException;

	public SystemAccountBo getSysAccount(Long id) throws RuntimeException; 
}
