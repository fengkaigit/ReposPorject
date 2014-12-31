package com.ey.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dao.entity.BaseRuleFee;
import com.ey.dao.entity.ServicePreferential;
import com.ey.dao.entity.UserBase;
import com.ey.service.FeeService;
import com.ey.service.PoundageService;
@Service("poundageService")
public class PoundageServiceImpl implements PoundageService{
	@Autowired
	private FeeService feeService;
	@Override
	public double getPoundage(UserBase user,Integer paymentType,String areaId) {
		try{
			BaseRuleFee br = feeService.getFeeRule(paymentType, areaId);
			ServicePreferential sp = feeService.findServicePreferential(user.getId(),paymentType,areaId);
			Integer registType = user.getRegistType();
			Double poundage = null;
			poundage = br.getPersonalPoundage();
			if(registType!=null&&registType.intValue()==2){
				poundage = br.getUnitPoundage();
			}
			if(poundage!=null&&sp!=null&&sp.getPreferentialMoney()!=null){
				poundage = poundage +sp.getPreferentialMoney();
			}
			if(poundage==null||poundage.doubleValue()<0){
				poundage = 0d;
			}
			return poundage;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return 0;
	}

}
