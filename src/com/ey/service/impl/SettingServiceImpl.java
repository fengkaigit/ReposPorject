package com.ey.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dao.SettingDAO;
import com.ey.dao.entity.HosterSetting;
import com.ey.dao.entity.HosterSettingId;
import com.ey.dao.entity.PaymentSetting;
import com.ey.service.SettingService;
@Service("settingService")
public class SettingServiceImpl implements SettingService {
	@Autowired
	private SettingDAO settingDAO;

	@Override
	public List<PaymentSetting> list(Long userId, Integer paymentType)  throws RuntimeException{
		// TODO Auto-generated method stub
		return settingDAO.list(userId,paymentType);
	}

	@Override
	public Object getObjectById(Class c, Serializable id)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return settingDAO.get(c, id);
	}

	@Override
	public Long saveSetting(PaymentSetting setting)  throws RuntimeException {
		Long id = settingDAO.saveSetting(setting);
		return id;
	}

	@Override
	public void delSetting(Long id) throws RuntimeException {
		settingDAO.delSetting(id);
	}

	@Override
	public Long getSettingsByBillNumber(String billNumber, Integer paytype,Long userId)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return settingDAO.getSettingsByBillNumber(billNumber,paytype,userId);
	}

	@Override
	public List<PaymentSetting> getSettingByBillNumber(String name,
			Integer paymentType, Long userId) {
		// TODO Auto-generated method stub
		return settingDAO.getSettingByBillNumber(name,paymentType,userId);
	}

	@Override
	public Long saveHoster(PaymentSetting setting) throws RuntimeException {
		// TODO Auto-generated method stub
		return settingDAO.saveSetting(setting);
	}

	@Override
	public void delHoster(Long id) throws RuntimeException {
		settingDAO.delHoster(id);
	}

	@Override
	public void saveHosterDetailRelation(Long hosterId, Long id)
			throws RuntimeException {
		HosterSettingId hosterDetail = new HosterSettingId(hosterId,id);
		HosterSetting hoster = new HosterSetting(hosterDetail);
		settingDAO.save(hoster);
	}
}
