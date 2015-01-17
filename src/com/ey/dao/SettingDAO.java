package com.ey.dao;

import java.util.List;

import com.ey.bo.HosterBo;
import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.PaymentSetting;

public interface SettingDAO extends BaseDAO {

	List<PaymentSetting> list(Long userId, Integer paymentType) throws RuntimeException;

	Long saveSetting(PaymentSetting setting) throws RuntimeException;

	void delSetting(Long id)throws RuntimeException;

	Long getSettingsByBillNumber(String billNumber, Integer paytype,Long userId)throws RuntimeException;

	List<PaymentSetting> getSettingByBillNumber(String name,
			Integer paymentType, Long userId);
	
	public void delHoster(Long id) throws RuntimeException;

	public List<HosterBo> getHosterList(Long id);

}
