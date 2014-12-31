package com.ey.service;
import java.util.Date;
import java.util.List;

import com.ey.bo.BaseRuleFeeBo;
import com.ey.dao.entity.BaseRuleFee;
import com.ey.dao.entity.FeeRule;
import com.ey.dao.entity.ServicePreferential;

public interface FeeService {
	public FeeRule getFeeRule(int payType,Date date)throws RuntimeException;

	public List<BaseRuleFeeBo> findRulesByArea(Integer paymentType,String areaId,String cityId, Integer page,
			Integer rows);
	public Long findRulesTotalByArea(Integer paymentType,String areaId,String cityId);

	public void saveFeeRule(BaseRuleFee ruleFee);

	public void delFeeRules(Integer paymentType, String areaId);

	public BaseRuleFee getFeeRule(Integer paymentType, String areaId);

	public ServicePreferential findServicePreferential(Long userId,
			Integer paymentType, String areaId);
}
