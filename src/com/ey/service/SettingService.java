package com.ey.service;

import java.io.Serializable;
import java.util.List;

import com.ey.dao.entity.PaymentSetting;

public interface SettingService {
	public List<PaymentSetting> list(Long userId,Integer paymentType) throws RuntimeException;
	Object getObjectById(Class c,Serializable id) throws RuntimeException;
	public Long saveSetting(PaymentSetting setting)  throws RuntimeException;
	public void delSetting(Long id) throws RuntimeException;
	public Long getSettingsByBillNumber(String billNumber, Integer paytype,Long userId) throws RuntimeException;
	public List<PaymentSetting> getSettingByBillNumber(String name,
			Integer paymentType, Long userId);
	public Long saveHoster(PaymentSetting setting) throws RuntimeException;
	public void delHoster(Long id) throws RuntimeException;
	public void saveHosterDetailRelation(Long hosterId, Long id) throws RuntimeException;

}
