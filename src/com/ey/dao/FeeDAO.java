package com.ey.dao;

import java.util.Date;
import java.util.List;

import com.ey.bo.BaseRuleFeeBo;
import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.BaseRuleFee;
import com.ey.dao.entity.FeeRule;
import com.ey.dao.entity.ServicePreferential;

public interface FeeDAO extends BaseDAO {

	FeeRule getFeeRule(int payType, Date date) throws RuntimeException;

	List<BaseRuleFeeBo> findRulesByArea(Integer paymentType,String areaId,String cityId, Integer page,
			Integer rows);

	Long findRulesTotalByArea(Integer paymentType,String areaId,String cityId);

	void delFeeRules(Integer paymentType, String areaId);

	void saveFeeRule(BaseRuleFee ruleFee);

	BaseRuleFee getFeeRule(Integer paymentType, String areaId);

	ServicePreferential findServicePreferential(Long userId,
			Integer paymentType, String areaId);

}
