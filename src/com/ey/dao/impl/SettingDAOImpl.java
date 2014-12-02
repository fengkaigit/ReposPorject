package com.ey.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.dao.SettingDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.PaymentSetting;

@Repository("settingDAO")
public class SettingDAOImpl extends BaseDAOImpl implements SettingDAO {

	@Override
	public java.util.List<PaymentSetting> list(Long userId, Integer paymentType)
			throws RuntimeException {
		StringBuffer hql = new StringBuffer(
				"from PaymentSetting where userId=? and delFlag=?");
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
	public void saveSetting(PaymentSetting setting) throws RuntimeException {
		if (setting.getId() == null) {
			super.getDbId(setting);
		}
		saveOrUpdate(setting);
	}

	@Override
	public void delSetting(Long id) {
		// TODO Auto-generated method stub
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
		String hql = "from PaymentSetting where billNumber like ? and paymentType=? and userId=?";

		return find(hql, new Object[] { "%" + name + "%", paymentType, userId });
	}

}
