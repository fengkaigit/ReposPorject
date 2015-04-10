package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.bo.HosterBo;
import com.ey.dao.SettingDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.PaymentSetting;

@Repository("settingDAO")
public class SettingDAOImpl extends BaseDAOImpl implements SettingDAO {

	@Override
	public java.util.List<PaymentSetting> list(Long userId, Integer paymentType)
			throws RuntimeException {
		StringBuffer hql = new StringBuffer(
				"from PaymentSetting where userId=? and delFlag=? and paymentType!=-1");
		List<Object> param = new ArrayList();
		param.add(userId);
		param.add(0);
		if (paymentType != null) {
			hql.append(" and paymentType=?");
			param.add(paymentType);
		}
		hql.append(" order by paymentType,createTime");
		return this.find(hql.toString(), param);
	}

	@Override
	public Long saveSetting(PaymentSetting setting) throws RuntimeException {
		Long id = 0l;
		if (setting.getId() == null) {
			super.getDbId(setting);
			id = setting.getId();
		}
		saveOrUpdate(setting);
		return id;
	}

	@Override
	public void delSetting(Long id) {
		String hql = "update PaymentSetting set delFlag=1 where id=?";
		this.executeHql(hql, new Object[] { id });
	}

	@Override
	public Long getSettingsByBillNumber(String billNumber, Integer paytype,
			Long userId) throws RuntimeException {
		String hql = "select count(id) from PaymentSetting where billNumber=? and paymentType=? and userId=?";
		List<Object> list = find(hql, new Object[] { billNumber, paytype,
				userId });
		if (list != null && list.size() > 0) {
			return new Long(list.get(0) + "");
		} else {
			return 0l;
		}
	}

	@Override
	public List<PaymentSetting> getSettingByBillNumber(String name,
			Integer paymentType, Long userId) {
		String hql = "from PaymentSetting where (billNumber like ? or hoster like ?) and paymentType=? and userId=?";

		return find(hql, new Object[] { "%" + name + "%","%" + name + "%", paymentType, userId });
	}

	@Override
	public void delHoster(Long id) throws RuntimeException {
		String hql = "update PaymentSetting set delFlag=1 where id in (select id.detailId from HosterSetting where id.hosterId=?)";
		this.executeHql(hql, new Object[] { id });
		hql = "update PaymentSetting set delFlag=1 where id=?";
		this.executeHql(hql, new Object[] { id });
	}

	@Override
	public List<HosterBo> getHosterList(Long id) {
		String hql = "select new com.ey.bo.HosterBo(a.areaId, a.areaName, a.hoster,a.payAddress,a.groupId,a.groupName,a.id) " +
				"from PaymentSetting a where userId=? and paymentType=? and delFlag=?";
		return this.find(hql,new Object[]{id,-1,0});
	}

	@Override
	public java.util.List<PaymentSetting> getDetailByHoster(Long userId,
			Long hosterId, Integer paymentType) {
		StringBuffer hql = new StringBuffer(
				"from PaymentSetting where userId=? and delFlag=? and id in (select id.detailId from HosterSetting where id.hosterId=?) and paymentType<>?");
		List<Object> param = new ArrayList();
		param.add(userId);
		param.add(0);
		param.add(hosterId);
		param.add(-1);
		if (paymentType != null) {
			hql.append(" and paymentType=?");
			param.add(paymentType);
		}
		hql.append(" order by paymentType,createTime");
		return this.find(hql.toString(), param);
	}

}
