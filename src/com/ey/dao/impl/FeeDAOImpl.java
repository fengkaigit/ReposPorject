package com.ey.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.dao.FeeDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.FeeRule;
@Repository("feeDAO")
public class FeeDAOImpl extends BaseDAOImpl implements FeeDAO {

	@Override
	public FeeRule getFeeRule(int payType, Date date) throws RuntimeException{
		String hql = "from FeeRule where paymentType=? and beginTime<=? and endTime>=? order by id desc";
		List<FeeRule> list = this.find(hql,new Object[]{payType,date,date});
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
