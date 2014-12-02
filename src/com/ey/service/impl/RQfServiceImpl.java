package com.ey.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dao.JfDAO;
import com.ey.dao.entity.BaseCustomValue;
import com.ey.dao.entity.PayAccountBill;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.PaymentGas;
import com.ey.dao.entity.PaymentSetting;
import com.ey.forms.JfForm;
import com.ey.service.RQfService;
import com.ey.service.SettingService;
import com.ey.service.StaticService;
import com.ey.util.DateUtil;
import com.ey.util.StringUtil;
import com.ey.util.UUIdUtil;

@Service("rqfService")
public class RQfServiceImpl implements RQfService {
	@Autowired
	private JfDAO jfDAO;
	@Autowired
	SettingService settingService;
	@Autowired
	private StaticService staticService;
	@Override
	public void saveBill(JfForm form) throws RuntimeException {
		Long billId = form.getBillId();
		PayAccountBill payAccountBill = null;
		PaymentBill paymentBill = null;
		PaymentGas paymentGas = null;
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
			//String areaId, String areaName, Long agentId, String agentName,
			//String orderNumber, String remarks, String payAddress
			paymentBill = new PaymentBill(null, null, form.getUserId(), 0,
					date, form.getBillMoney(), form.getBillMoney(), 0, form
							.getPoundage(), form.getPayType(), form.getEntId(),
					form.getBusinessType(), form.getPaymentStatus(), form
							.getPaymentMode(), UUIdUtil.getUUId(), form.getDivideStatus(),form.getAreaId(),form.getAreaName(),form.getAgentId(),form.getAgentName(),form.getBillNo(),form.getRemark(),form.getPayAddress(),form.getYear(),new Integer(form.getMonth()));
			String begin = form.getYear() + "-" + form.getMonth()
					+ "-01 00:00:01";
			String end = form.getYear() + "-" + form.getMonth() + "-"+DateUtil.getLastDayOfMonth(form.getYear(), new Integer(form.getMonth()))+" 23:59:59";

			paymentGas = new PaymentGas(null, form.getUserId(), 0l,
					DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", begin),
					DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", end),
					form.getPeriodFrequency(), form.getBillMoney(), form
							.getPoundage(), date, form.getBillNumber());
			saveSetting(form, date);
			jfDAO.saveBill(payAccountBill,paymentBill,paymentGas);
			form.setBillId(payAccountBill.getId());
			form.setBillNo(paymentBill.getOrderNumber());
			form.setId(paymentBill.getId());
			form.setBillDate(DateUtil.getDateTime("yyyy年M月d日",date));
			
		}else{
			paymentBill.setPaymentStatus(form.getPaymentStatus());
			paymentBill.setRemarks(form.getRemark());
			paymentBill.setPayAddress(form.getPayAddress());
			jfDAO.saveOrUpdate(paymentBill);
		}

	}

	@Override
	public Object getObjectById(Class c,Serializable id) throws RuntimeException {
		// TODO Auto-generated method stub
		return jfDAO.get(c, id);
	}
	private void saveSetting(JfForm form, Date date) {
		PaymentSetting setting = null;
		if (form.getBczh() != null && form.getBczh()
				&& (!StringUtil.isEmptyString(form.getBillNumber()))) {
			Long count = settingService.getSettingsByBillNumber(form
					.getBillNumber(),form.getPayType(),form.getUserId());
			if (count.longValue() == 0) {
				setting = new PaymentSetting(null, form.getUserId(), 0, "自家",
						form.getAreaId(), form.getAreaName(),
						form.getPayType(), null, form.getEntId(), form
								.getEndName(), form.getBillNumber(), form
								.getPayAddress(), date, 0, form.getUserName());
				List<BaseCustomValue> paymentTypes = staticService
						.listValues("payment_type");
				for (BaseCustomValue paymentType : paymentTypes) {
					if (paymentType.getId().getDataValue().intValue() == form
							.getPayType().intValue()) {
						setting.setPaymentTypeName(paymentType.getPropChName());
						break;
					}
				}
				settingService.saveSetting(setting);
			}

		}
	}
}
