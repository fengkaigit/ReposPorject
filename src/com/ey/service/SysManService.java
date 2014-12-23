package com.ey.service;

import java.util.List;
import java.util.Map;

import com.ey.dao.entity.Feedback;
import com.ey.dao.entity.SystemManager;

public interface SysManService {
	
    void deleteBySysManIds(String[] ids) throws RuntimeException;
    void saveSysMan(SystemManager sysMan) throws RuntimeException;
    void updateSysman(SystemManager sysMan) throws RuntimeException;
	List<SystemManager> findManagers(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
	SystemManager findManagerByLoginName(String loginName,String password) throws RuntimeException;
	Long findManagerByLoginName(String loginName) throws RuntimeException;
	SystemManager getSySManager(Long id) throws RuntimeException;
	void updatePassById(Long id,String password) throws RuntimeException;
	void saveFeedBack(Feedback feedBack) throws RuntimeException;
	void updateReplyFeedById(Long id,String replyContent) throws RuntimeException;
	List<Feedback> findFeedBacks(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
	void deleteFeedBackByIds(String[] ids)throws RuntimeException;
	void updateStatusByIds(String[] ids,Integer status)throws RuntimeException;
	Feedback getFeedBack(Long id) throws RuntimeException;
	Long findTotalFeedBack(Map<String,Object> Qparam) throws RuntimeException;
}
