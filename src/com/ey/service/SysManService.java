package com.ey.service;

import java.util.List;
import java.util.Map;

import com.ey.dao.entity.SystemManager;

public interface SysManService {
	
    void deleteBySysManIds(String[] ids) throws RuntimeException;
    void saveSysMan(SystemManager sysMan) throws RuntimeException;
	List<SystemManager> findManagers(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
	SystemManager findManagerByLoginName(String loginName,String password) throws RuntimeException;
	SystemManager findManagerByLoginName(String loginName) throws RuntimeException;
	SystemManager getSySManager(Long id) throws RuntimeException;
	void updatePassById(Long id,String password) throws RuntimeException;
}
