package com.ey.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.bo.AgentBo;
import com.ey.dao.JfDAO;
import com.ey.dao.entity.BaseCustomValue;
import com.ey.dao.entity.PayAccountBill;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.PaymentElectic;
import com.ey.dao.entity.PaymentSetting;
import com.ey.forms.JfForm;
import com.ey.service.AgentService;
import com.ey.service.BankAccountService;
import com.ey.service.DfService;
import com.ey.service.SettingService;
import com.ey.service.StaticService;
import com.ey.service.TransferService;
import com.ey.util.DateUtil;
import com.ey.util.StringUtil;
import com.ey.util.UUIdUtil;

@Service("dfService")
public class DfServiceImpl implements DfService {
	@Autowired
	private JfDAO jfDAO;
	@Autowired
	SettingService settingService;
	@Autowired
	private StaticService staticService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private TransferService transferService;
	@Autowired
	BankAccountService bankAccountService;
	@Override
	public void saveBill(JfForm form)  throws RuntimeException{
		Long billId = form.getBillId();
		PayAccountBill payAccountBill = null;
		PaymentBill paymentBill = null;
		PaymentElectic paymentElectic = null;
		if (!StringUtil.isNullObject(billId)) {
			payAccountBill = (PayAccountBill) jfDAO.get(PayAccountBill.class,
					billId);
			paymentBill = (PaymentBill)jfDAO.get(PaymentBill.class,
					form.getId());
		}
		if (payAccountBill == null) {
			Date date = new Date();
			Map<String,Object> param = new HashMap();
			param.put("areaId", form.getAreaId());
			List<AgentBo> agents = agentService.getAllAgent(param, 0, 0);
			if(agents!=null&&agents.size()>0){
				form.setAgentId(agents.get(0).getId());
				form.setAgentName(agents.get(0).getRegistRealName());
			}
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
							.getPaymentMode(), null, form.getDivideStatus(),form.getAreaId(),form.getAreaName(),form.getAgentId(),form.getAgentName(),form.getBillNo(),form.getRemark(),form.getPayAddress(),form.getYear(),new Integer(form.getMonth()));
			String begin = form.getYear() + "-" + form.getMonth()
					+ "-01 00:00:01";
			String end = form.getYear() + "-" + form.getMonth() + "-"+DateUtil.getLastDayOfMonth(form.getYear(), new Integer(form.getMonth()))+" 23:59:59";

			paymentElectic = new PaymentElectic(null, form.getUserId(), 0l,
					DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", begin),
					DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", end),
					form.getPeriodFrequency(), form.getBillMoney(), form
							.getPoundage(), date, form.getBillNumber());
			saveSetting(form, date);
			if (form.getOrderNum()!=null)
				paymentBill.setOrderNumber(form.getOrderNum());
			jfDAO.saveBill(payAccountBill,paymentBill,paymentElectic);
			form.setBillId(payAccountBill.getId());
			form.setBillNo(paymentBill.getOrderNumber());
			form.setId(paymentBill.getId());
			form.setBillDate(DateUtil.getDateTime("yyyy年M月d日",date));
			
		}else{
			paymentBill.setPaymentStatus(form.getPaymentStatus());
			paymentBill.setRemarks(form.getRemark());
			paymentBill.setPayAddress(form.getPayAddress());
			jfDAO.saveOrUpdate(paymentBill);
			transferService.saveTransferRecord(form);
			bankAccountService.saveBankAccount(form);
		}

	}

	@Override
	public Object getObjectById(Class c,Serializable id)  throws RuntimeException{
		// TODO Auto-generated method stub
		return jfDAO.get(c, id);
	}
	public void saveSetting(JfForm form, Date date) throws RuntimeException {
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
