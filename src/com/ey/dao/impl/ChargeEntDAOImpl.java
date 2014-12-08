package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ey.bo.ChargeEntBo;
import com.ey.dao.ChargeEntDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.ChargeEnterprise;
import com.ey.util.StringUtil;


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
		List paramList = new ArrayList();
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
		String hql = "from ChargeEnterprise where areaId=? and delFlag = 0 order by id";
		return this.find(hql, new Object[]{areaId});
	}

	@Override
	public List<ChargeEnterprise> getChargesByArea(String areaId, int payType) {
		String hql = "from ChargeEnterprise where areaId=? and payType=? and delFlag = 0 order by id";
		return this.find(hql, new Object[]{areaId,payType});
	}

	@Override
	public BankAccount getBankAccount(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select b from EntAccountRelation a,BankAccount b where a.id.cardNumber = b.id and a.id.id = ? and a.flag = ?";
		List<BankAccount> list = this.find(hql, new Object[]{id,false});
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}

	@Override
	public Long getTotalByParam(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		List paramList = new ArrayList();
		StringBuffer hql=new StringBuffer("select count(a.id) from ChargeEnterprise a where a.delFlag = 0");
		createQueryParam(hql,Qparam,paramList);
		List list = this.find(hql.toString(),paramList.toArray());
		if(list!=null&&list.size()>0){
			return Long.valueOf(list.get(0)+"");
		}
		return 0l;
	}
	private void createQueryParam(StringBuffer query,Map<String, Object> Qparam,List paramList){
		if(Qparam!=null&&Qparam.size()>0){
			String name = (String)Qparam.get("name");
			Integer payType = (Integer)Qparam.get("payType");
			String areaId = (String)Qparam.get("areaId");
			String cardNumber = (String)Qparam.get("careNumber");
			if(!StringUtil.isEmptyString(name)){
				query.append(" and a.enterpriseName like ?");
				paramList.add("%"+ name +"%");
			}
			if(payType!=null){
				query.append(" and a.payType = ?");
				paramList.add(payType);
			}
			if(!StringUtil.isEmptyString(areaId)){
				query.append(" and a.areaId = ?");
				paramList.add(areaId);
			}
			if(!StringUtil.isEmptyString(cardNumber)){
				query.append(" and a.careNumber = ?");
				paramList.add(cardNumber);
			}
		}
	}
}
