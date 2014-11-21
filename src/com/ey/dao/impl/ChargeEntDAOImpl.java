package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ey.bo.ChargeEntBo;
import com.ey.dao.ChargeEntDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.ChargeEnterprise;


@Repository("chargeEntDAO")
public class ChargeEntDAOImpl extends BaseDAOImpl implements ChargeEntDAO {

	@Override
	public void deleteByEntId(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		 String hql = "update ChargeEnterprise set delFlag = ? where id = ?";
         this.executeHql(hql, new Object[]{true,id});
	}

	@Override
	public List<ChargeEntBo> getAllChargeEnt(
			Map<String, Object> Qparam, Integer page, Integer rows)
			throws RuntimeException {
		// TODO Auto-generated method stub
		//List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer("select new com.ey.bo.ChargeEntBo(a.id,a.areaId,a.enterpriseName,a.careNumber,a.payType,a.exPic,b.province,b.namePath,b.encodePath,c.propChName) from ChargeEnterprise a,Area b,BaseCustomValue c where a.areaId = b.id and c.id.customEngName ='payment_type' and c.id.dataValue = a.payType and a.delFlag = 0");
		return this.find(hql.toString(),page, rows);
	}

	@Override
	public ChargeEntBo getChargeEnt(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select new com.ey.bo.ChargeEntBo(a.id,a.areaId,a.enterpriseName,a.careNumber,a.payType,a.exPic,b.province,b.namePath,b.encodePath) from ChargeEnterprise a,Area b where a.areaId = b.id and a.id = ?";
		List<ChargeEntBo> list = this.find(hql, new Object[]{id});
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public List<ChargeEntBo> getChargesByArea(String areaId) {
		String hql = "from ChargeEnterprise where areaId=? order by id";
		return this.find(hql, new Object[]{areaId});
	}

	@Override
	public List<ChargeEnterprise> getChargesByArea(String areaId, int payType) {
		String hql = "from ChargeEnterprise where areaId=? and payType=? order by id";
		return this.find(hql, new Object[]{areaId,payType});
	}

}
