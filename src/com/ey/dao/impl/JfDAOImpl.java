package com.ey.dao.impl;

import org.springframework.stereotype.Repository;

import com.ey.dao.JfDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.PayAccountBill;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.PaymentWater;
@Repository("jfDAO")
public class JfDAOImpl extends BaseDAOImpl implements JfDAO {

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentWater paymentWater) {
		if(payAccountBill.getId()==null){
			super.getDbId(payAccountBill);
		}
		if(paymentBill.getAccountBillId()==null){
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if(paymentBill.getId()==null){
			super.getDbId(paymentBill);
		}
		if(paymentWater.getId()==null){
			paymentWater.setId(payAccountBill.getId());
		}
		save(payAccountBill);
		save(paymentBill);
		save(paymentWater);
	}

}
