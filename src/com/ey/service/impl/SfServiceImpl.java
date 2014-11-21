package com.ey.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dao.JfDAO;
import com.ey.dao.entity.PayAccountBill;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.PaymentWater;
import com.ey.forms.JfForm;
import com.ey.service.SfService;
import com.ey.util.DateUtil;
import com.ey.util.StringUtil;

@Service("sfService")
public class SfServiceImpl implements SfService {
	@Autowired
	private JfDAO jfDAO;

	@Override
	public void saveBill(JfForm form) {
		Long billId = form.getBillId();
		PayAccountBill payAccountBill = null;
		PaymentBill paymentBill = null;
		PaymentWater paymentWater = null;
		if (!StringUtil.isNullObject(billId)) {
			payAccountBill = (PayAccountBill) jfDAO.get(PayAccountBill.class,
					billId);
			paymentBill = (PaymentBill)jfDAO.get(PaymentBill.class,
					form.getId());
		}
		if (payAccountBill == null) {
			Date date = new Date();
			payAccountBill = new PayAccountBill(null, form.getUserId(), date,
					0l, form.getBillMoney(), form.getPoundage(), form
							.getPayType(), form.getBillType(), form.getEntId(),
					form.getBusinessType());
			paymentBill = new PaymentBill(null, null, form.getUserId(), 0,
					date, form.getBillMoney(), form.getBillMoney(), 0, form
							.getPoundage(), form.getPayType(), form.getEntId(),
					form.getBusinessType(), form.getPaymentStatus(), form
							.getPaymentMode(), null, form.getDivideStatus());
			String begin = form.getYear() + "-" + form.getMonth()
					+ "-01 00:00:01";
			String end = form.getYear() + "-" + form.getMonth() + "-30 23:59:59";

			paymentWater = new PaymentWater(null, form.getUserId(), 0l,
					DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", begin),
					DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", end),
					form.getPeriodFrequency(), form.getBillMoney(), form
							.getPoundage(), date, form.getBillNumber());
			jfDAO.saveBill(payAccountBill,paymentBill,paymentWater);
			form.setBillId(payAccountBill.getId());
			form.setId(paymentBill.getId());
			form.setBillDate(DateUtil.getDateTime("yyyy年M月d日",date));
			
		}else{
			paymentBill.setPaymentStatus(form.getPaymentStatus());
			jfDAO.saveOrUpdate(paymentBill);
		}

	}

	@Override
	public Object getObjectById(Class c,Serializable id) {
		// TODO Auto-generated method stub
		return jfDAO.get(c, id);
	}
}
