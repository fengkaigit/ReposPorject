package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ey.dao.AnnounceDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.SysAnnouncement;
import com.ey.util.StringUtil;


@Repository("announceDAO")
public class AnnounceDAOImpl extends BaseDAOImpl implements AnnounceDAO {

	@Override
	public java.util.List<SysAnnouncement> getAnnouncesByQueryParam(
			Map<String, Object> Qparam, Integer page, Integer rows)
			throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("from SysAnnouncement where 1=1");
		createQueryParam(hql,Qparam,paramList);
		hql.append(" order by createTime desc");
		return this.find(hql.toString(),paramList.toArray(), page, rows);
	}
	private void createQueryParam(StringBuffer query,Map<String, Object> Qparam,List paramList){
		if(Qparam!=null&&Qparam.size()>0){
			String title = (String)Qparam.get("title");
			Integer scope = (Integer)Qparam.get("scope");
			Integer group = (Integer)Qparam.get("group");
			String areaId = (String)Qparam.get("areaId");
			Integer status = (Integer)Qparam.get("status");
			if(!StringUtil.isEmptyString(title)){
				query.append(" and title like ?");
				paramList.add("%"+ title +"%");
			}
			if(scope!=null){
				query.append(" and announcementScope = ?");
				paramList.add(scope);
			}
			if(group!=null){
				query.append(" and announcementGroup = ?");
				paramList.add(group);
			}
			if(status!=null){
				query.append(" and status = ?");
				paramList.add(status);
			}
			if(!StringUtil.isEmptyString(areaId)){
				query.append(" and areaId = ?");
				paramList.add(areaId);
			}
		}
	}
	@Override
	public void deleteByAnnounceId(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		this.executeHql("delete from SysAnnouncement where id = ?", new Object[]{id});
	}

}
