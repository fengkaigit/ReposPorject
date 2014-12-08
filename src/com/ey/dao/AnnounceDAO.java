package com.ey.dao;

import java.util.List;
import java.util.Map;

import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.SysAnnouncement;

public interface AnnounceDAO extends BaseDAO {
	
    List<SysAnnouncement> getAnnouncesByQueryParam(Map<String,Object> Qparam,Integer page,Integer rows) throws RuntimeException;
    
	void deleteByAnnounceId(Long id) throws RuntimeException;
	
    Long getAnnouncesTotalByQueryParam(Map<String,Object> Qparam) throws RuntimeException;

    void updateStatusById(Long id ,Integer status) throws RuntimeException;

}
