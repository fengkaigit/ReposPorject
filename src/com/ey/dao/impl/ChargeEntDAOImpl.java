package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.bo.AgentBo;
import com.ey.bo.ChargeEntBo;
import com.ey.dao.ChargeEntDAO;
import com.ey.dao.base.impl.BaseDAOImpl;


@Repository("chargeEntDAO")
public class ChargeEntDAOImpl extends BaseDAOImpl implements ChargeEntDAO {

	@Override
	public void deleteByEntId(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		 String hql = "delete from ChargeEnterprise where id = ?";
         this.executeHql(hql, new Object[]{id});
	}

	@Override
	public List<ChargeEntBo> getAllChargeEnt(
			Map<String, Object> Qparam, Integer page, Integer rows)
			throws RuntimeException {
		// TODO Auto-generated method stub
		//List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("select new com.ey.bo.ChargeEntBo(a.id,a.areaId,a.enterpriseName,a.careNumber,a.payType,b.province) from ChargeEnterprise a,Area b where a.areaId = b.id");
		return this.find(hql.toString(),page, rows);
	}

	@Override
	public ChargeEntBo getChargeEnt(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select new com.ey.bo.ChargeEntBo(a.id,a.areaId,a.enterpriseName,a.careNumber,a.payType,b.province) from ChargeEnterprise a,Area b where a.areaId = b.id and a.id = ?";
		List<ChargeEntBo> list = this.find(hql, new Object[]{id});
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

}
