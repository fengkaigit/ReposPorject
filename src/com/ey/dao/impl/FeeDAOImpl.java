package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.bo.BaseRuleFeeBo;
import com.ey.dao.FeeDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.BaseRuleFee;
import com.ey.dao.entity.FeeRule;
import com.ey.dao.entity.ServicePreferential;
import com.ey.util.StringUtil;
@Repository("feeDAO")
public class FeeDAOImpl extends BaseDAOImpl implements FeeDAO {
	@Override
	public FeeRule getFeeRule(int payType, Date date) throws RuntimeException{
		String hql = "from FeeRule where paymentType=? and beginTime<=? order by id desc";
		List<FeeRule> list = this.find(hql,new Object[]{payType,date});
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<BaseRuleFeeBo> findRulesByArea(Integer paymentType,String areaId,String cityId, Integer page,
			Integer rows) {
		StringBuffer hql = new StringBuffer("select new com.ey.bo.BaseRuleFeeBo(a.areaId,b.province, a.paymentType,a.personalPoundage, a.unitPoundage) from BaseRuleFee a,Area b where a.areaId=b.id");
		List<Object> params = buildWhere(hql,paymentType,areaId,cityId);
		hql.append(" order by a.areaId,a.paymentType");
		return find(hql.toString(), params,page,rows);
	}

	@Override
	public Long findRulesTotalByArea(Integer paymentType,String areaId,String cityId) {
		StringBuffer hql = new StringBuffer("select count(a.areaId) from BaseRuleFee a,Area b where a.areaId=b.id");
		List<Object> params = buildWhere(hql,paymentType,areaId,cityId);
		return count(hql.toString(), params);
	}
	List<Object> buildWhere(StringBuffer hql,Integer paymentType,String areaId,String cityId){
		List<Object> params = new ArrayList();
		if(!StringUtil.isEmptyString(areaId)){
			if(StringUtil.isEmptyString(cityId)){
				hql.append(" and b.city=?");
				params.add(areaId);
			}else{
				hql.append(" and a.areaId=?");
				params.add(cityId);
			}
			
		}
		if(paymentType!=null&&paymentType.intValue()>-1){
			hql.append(" and a.paymentType =?");
			params.add(paymentType);
		}
		return params;
	}

	@Override
	public void delFeeRules(Integer paymentType, String areaId) {
		String hql = "delete from BaseRuleFee where areaId=? and paymentType=?";
		executeHql(hql, new Object[]{areaId,paymentType});
	}

	@Override
	public void saveFeeRule(BaseRuleFee ruleFee) {
		this.saveOrUpdate(ruleFee);
	}

	@Override
	public BaseRuleFee getFeeRule(Integer paymentType, String areaId) {
		return (BaseRuleFee)get("from BaseRuleFee where areaId=? and paymentType=?", new Object[]{areaId,paymentType});
	}

	@Override
	public ServicePreferential findServicePreferential(Long userId,
			Integer paymentType, String areaId) {
		Date date = new Date();
		return (ServicePreferential)get("from ServicePreferential where userId=? and paymentType=? and areaId=? and beginTime<=? and endTime>=? order by beginTime desc",new Object[]{userId,paymentType,areaId,date,date});
	}
}
