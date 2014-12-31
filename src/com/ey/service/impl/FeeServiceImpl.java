package com.ey.service.impl;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.bo.BaseRuleFeeBo;
import com.ey.dao.FeeDAO;
import com.ey.dao.entity.BaseRuleFee;
import com.ey.dao.entity.FeeRule;
import com.ey.dao.entity.ServicePreferential;
import com.ey.service.FeeService;
@Service("feeService")
public class FeeServiceImpl implements FeeService {
	@Autowired
	private FeeDAO feeDAO;
	@Override
	public FeeRule getFeeRule(int payType, Date date) throws RuntimeException{
		return feeDAO.getFeeRule(payType,date);
	}
	@Override
	public List<BaseRuleFeeBo> findRulesByArea(Integer paymentType,String areaId,String cityId, Integer page,
			Integer rows) {
		return feeDAO.findRulesByArea(paymentType,areaId,cityId,page,rows);
	}
	@Override
	public Long findRulesTotalByArea(Integer paymentType,String areaId,String cityId) {
		return feeDAO.findRulesTotalByArea(paymentType,areaId,cityId);
	}
	@Override
	public void delFeeRules(Integer paymentType, String areaId) {
		feeDAO.delFeeRules(paymentType,areaId);
	}
	@Override
	public void saveFeeRule(BaseRuleFee ruleFee) {
		feeDAO.saveFeeRule(ruleFee);
	}
	@Override
	public BaseRuleFee getFeeRule(Integer paymentType, String areaId) {
		// TODO Auto-generated method stub
		return feeDAO.getFeeRule(paymentType,areaId);
	}
	@Override
	public ServicePreferential findServicePreferential(Long userId,
			Integer paymentType, String areaId) {
		return feeDAO.findServicePreferential(userId,paymentType,areaId);
	}

}
