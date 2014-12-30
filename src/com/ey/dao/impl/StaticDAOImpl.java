package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.BankInfo;
import com.ey.dao.entity.BaseCustomProp;
import com.ey.dao.entity.BaseCustomValue;
import com.ey.util.StringUtil;
import com.ey.dao.StaticDAO;
@Repository("staticDAO")
public class StaticDAOImpl extends BaseDAOImpl implements StaticDAO {

	@Override
	public java.util.List<BaseCustomProp> listProps(String typeCode) {
		StringBuffer buffer = new StringBuffer("from BaseCustomProp");
		List<Object> params = new ArrayList();
		if(!StringUtil.isEmptyString(typeCode)){
			buffer.append(" where propEngName=?");
			params.add(typeCode);
		}
		return this.find(buffer.toString(), params);
	}

	@Override
	public java.util.List<BaseCustomValue> listValues(String typeCode) {
		StringBuffer buffer = new StringBuffer("from BaseCustomValue");
		List<Object> params = new ArrayList();
		if(!StringUtil.isEmptyString(typeCode)){
			buffer.append(" where id.customEngName=?");
			params.add(typeCode);
		}
		buffer.append(" order by id.customEngName,id.dataValue");
		return this.find(buffer.toString(), params);
	}

	@Override
	public java.util.List<BankInfo> listBanks() throws RuntimeException {
		// TODO Auto-generated method stub
		return this.find("from BankInfo");
	}

	@Override
	public List findCustomProps(Map<String, Object> Qparam,
			Integer page, Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from BaseCustomProp where 1=1");
		List<Object> params = new ArrayList();
		createCustomPropQueryParams(hql,Qparam,params);
		return this.find(hql.toString(), params, page, rows);
	}

	@Override
	public List findCustomValues(Map<String, Object> Qparam,
			Integer page, Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from BaseCustomValue where 1=1");
		List<Object> params = new ArrayList();
		createCustomValueQueryParams(hql,Qparam,params);
		hql.append(" order by id.customEngName,id.dataValue");
		return this.find(hql.toString(), params, page, rows);
	}
	private void createCustomPropQueryParams(StringBuffer query,Map<String, Object> Qparam,List paramList){
		if(Qparam!=null&&Qparam.size()>0){
			String customPropName = (String)Qparam.get("customPropName");
			if(!StringUtil.isEmptyString(customPropName)){
				query.append(" and propEngName = ?");
				paramList.add(customPropName);
			}
		}
	}
	private void createCustomValueQueryParams(StringBuffer query,Map<String, Object> Qparam,List paramList){
		if(Qparam!=null&&Qparam.size()>0){
			String customPropName = (String)Qparam.get("customPropName");
			if(!StringUtil.isEmptyString(customPropName)){
				query.append(" and id.customEngName = ?");
				paramList.add(customPropName);
			}
		}
	}

	@Override
	public Long getTotalCustomProp(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select count(propEngName) from BaseCustomProp where 1=1");
		List<Object> params = new ArrayList();
		createCustomPropQueryParams(hql,Qparam,params);
		List list = this.find(hql.toString(), params);
		if(list!=null&&list.size()>0)
			return Long.valueOf(list.get(0)+"");
		return 0L;
	}

	@Override
	public Long getTotalCustomValue(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select count(propEngName) from BaseCustomValue where 1=1");
		List<Object> params = new ArrayList();
		createCustomValueQueryParams(hql,Qparam,params);
		List list = this.find(hql.toString(), params);
		if(list!=null&&list.size()>0)
			return Long.valueOf(list.get(0)+"");
		return 0L;
	}

	@Override
	public void deleteCustomProp(String customPropName) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "delete from BaseCustomProp where propEngName = ?";
		this.executeHql(hql, new Object[]{customPropName});
	}

	@Override
	public void deleteCustomValue(String customPropName, Integer customDataValue)
			throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "delete from BaseCustomValue where id.customEngName = ? and id.dataValue = ?";
		this.executeHql(hql, new Object[]{customPropName,customDataValue});
	}

	@Override
	public List findTransferRate(Map<String, Object> Qparam,
			Integer page, Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select new com.ey.bo.TransferRateBo(a.id,a.bankId,b.bankName,a.limitMoney,a.rate,a.cityFlag,a.peerFlag) from TransferRate a,BankInfo b where a.bankId = b.bankCode");
		List<Object> params = new ArrayList();
		createTransferRateQueryParams(hql,Qparam,params);
		return this.find(hql.toString(), params, page, rows);
	}

	@Override
	public Long getTotalTransferRate(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select count(id) from TransferRate where 1=1");
		List<Object> params = new ArrayList();
		createTransferRateQueryParams(hql,Qparam,params);
		List list = this.find(hql.toString(), params);
		if(list!=null&&list.size()>0)
			return Long.valueOf(list.get(0)+"");
		return 0L;
	}
	private void createTransferRateQueryParams(StringBuffer query,Map<String, Object> Qparam,List paramList){
		if(Qparam!=null&&Qparam.size()>0){
			
		}
	}

	@Override
	public List findNoticeInfos(Map<String, Object> Qparam,
			Integer page, Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from NoticeInfo where 1=1");
		List<Object> params = new ArrayList();
		createNoticeInfoQueryParams(hql,Qparam,params);
		hql.append(" order by sendStatus,createTime desc");
		return this.find(hql.toString(), params, page, rows);
	}

	@Override
	public Long getTotalNoticeInfo(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select count(id) from NoticeInfo where 1=1");
		List<Object> params = new ArrayList();
		createNoticeInfoQueryParams(hql,Qparam,params);
		List list = this.find(hql.toString(), params);
		if(list!=null&&list.size()>0)
			return Long.valueOf(list.get(0)+"");
		return 0L;
	}
	private void createNoticeInfoQueryParams(StringBuffer query,Map<String, Object> Qparam,List paramList){
		if(Qparam!=null&&Qparam.size()>0){
			
		}
	}

}
