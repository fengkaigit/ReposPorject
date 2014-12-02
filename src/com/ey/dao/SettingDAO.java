package com.ey.dao;

import java.util.List;

import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.PaymentSetting;

public interface SettingDAO extends BaseDAO {

	List<PaymentSetting> list(Long userId, Integer paymentType) throws RuntimeException;

	void saveSetting(PaymentSetting setting) throws RuntimeException;

	void delSetting(Long id)throws RuntimeException;

	Long getSettingsByBillNumber(String billNumber, Integer paytype,Long userId)throws RuntimeException;

	List<PaymentSetting> getSettingByBillNumber(String name,
			Integer paymentType, Long userId);

}
