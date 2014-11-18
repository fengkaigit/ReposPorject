package com.ey.dao;

import java.util.List;
import java.util.Map;

import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.SystemManager;


public interface SysManDAO extends BaseDAO {
	
	 void deleteByManId(Long id) throws RuntimeException;
	 List<SystemManager> findManagers(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
	 SystemManager findManagerByLoginName(String loginName,String password) throws RuntimeException;
     void updatePassById(Long id,String password) throws RuntimeException;


}
