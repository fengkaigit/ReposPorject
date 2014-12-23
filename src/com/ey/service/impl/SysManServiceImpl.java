package com.ey.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ey.dao.SysManDAO;
import com.ey.dao.common.dbid.DbidGenerator;
import com.ey.dao.entity.Feedback;
import com.ey.dao.entity.SystemManager;
import com.ey.service.SysManService;
import com.ey.util.StringUtil;

@Service("sysManService")
public class SysManServiceImpl implements SysManService {

	@Autowired
    private SysManDAO sysManDAO;
	
	@Override
	public List<SystemManager> findManagers(Map<String, Object> Qparam,
			Integer page, Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		return sysManDAO.findManagers(Qparam, page, rows);
	}

	@Override
	public SystemManager findManagerByLoginName(String loginName,
			String password) throws RuntimeException {
		// TODO Auto-generated method stub
		return sysManDAO.findManagerByLoginName(loginName, password);
	}

	@Override
	public void deleteBySysManIds(String[] ids) throws RuntimeException {
		// TODO Auto-generated method stub
		for(String id:ids){
        	if(StringUtil.isEmptyString(id))
        		continue;
        	sysManDAO.deleteByManId(Long.valueOf(id.trim()));
        }
	}

	@Override
	public void saveSysMan(SystemManager sysMan) throws RuntimeException {
		// TODO Auto-generated method stub
	    sysMan.setId(DbidGenerator.getDbidGenerator().getNextId());
		sysManDAO.save(sysMan);
	}

	@Override
	public SystemManager getSySManager(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		return (SystemManager)sysManDAO.get(SystemManager.class, id);
	}

	@Override
	public void updatePassById(Long id, String password)
			throws RuntimeException {
		// TODO Auto-generated method stub
		sysManDAO.updatePassById(id, password);
	}

	@Override
	public Long findManagerByLoginName(String loginName)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return sysManDAO.findManagerByLoginName(loginName);
	}

	@Override
	public void updateSysman(SystemManager sysMan) throws RuntimeException {
		// TODO Auto-generated method stub
		sysManDAO.update(sysMan);
	}

	@Override
	public void saveFeedBack(Feedback feedBack) throws RuntimeException {
		// TODO Auto-generated method stub
		feedBack.setId(DbidGenerator.getDbidGenerator().getNextId());
		sysManDAO.save(feedBack);
	}

	@Override
	public void updateReplyFeedById(Long id, String replyContent)
			throws RuntimeException {
		// TODO Auto-generated method stub
		sysManDAO.updateReplyFeedById(id, replyContent);
	}

	@Override
	public List<Feedback> findFeedBacks(Map<String, Object> Qparam,
			Integer page, Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		return sysManDAO.findFeedBacks(Qparam, page, rows);
	}

	@Override
	public void deleteFeedBackByIds(String[] ids) throws RuntimeException {
		// TODO Auto-generated method stub
		for(String id:ids){
        	if(StringUtil.isEmptyString(id))
        		continue;
        	sysManDAO.deleteFeedBackById(Long.valueOf(id.trim()));
        }
	}

	@Override
	public Feedback getFeedBack(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		return (Feedback)sysManDAO.get(Feedback.class, id);
	}

	@Override
	public Long findTotalFeedBack(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return sysManDAO.findTotalFeedBack(Qparam);
	}

	@Override
	public void updateStatusByIds(String[] ids,Integer status) throws RuntimeException {
		// TODO Auto-generated method stub
		sysManDAO.updateStatusByIds(ids, status);
	}

}
