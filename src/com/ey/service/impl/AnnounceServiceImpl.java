package com.ey.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dao.AnnounceDAO;
import com.ey.dao.entity.SysAnnouncement;
import com.ey.service.AnnounceService;
import com.ey.util.StringUtil;

@Service("announceService")
public class AnnounceServiceImpl implements AnnounceService {

	
	@Autowired
    private AnnounceDAO announceDAO;
	
	
	@Override
	public void saveObject(Object o) throws RuntimeException {
		// TODO Auto-generated method stub
		announceDAO.save(announceDAO.getDbId(o));
	}

	@Override
	public void updateObject(Object o) throws RuntimeException {
		// TODO Auto-generated method stub
		announceDAO.update(o);
	}

	@Override
	public SysAnnouncement getSysAnnouncement(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		return (SysAnnouncement)announceDAO.get(SysAnnouncement.class, id);
	}

	@Override
	public List<SysAnnouncement> getAnnouncesByQueryParam(
			Map<String, Object> Qparam, Integer page, Integer rows)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return announceDAO.getAnnouncesByQueryParam(Qparam, page, rows);
	}

	@Override
	public void deleteAnnounceByIds(String[] ids) throws RuntimeException {
		// TODO Auto-generated method stub
		 for(String id:ids){
         	if(StringUtil.isEmptyString(id))
         		continue;
         	announceDAO.deleteByAnnounceId(Long.valueOf(id.trim()));
         }
	}

	@Override
	public Long getAnnouncesTotalByQueryParam(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return announceDAO.getAnnouncesTotalByQueryParam(Qparam);
	}

	@Override
	public void updateStatusById(Long id, Integer status)
			throws RuntimeException {
		// TODO Auto-generated method stub
		announceDAO.updateStatusById(id, status);
	}

}
