package com.ey.dao;

import java.util.List;

import com.ey.bo.PaymentStatisBo;
import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.BillModel;
import com.ey.dao.entity.BillSubject;
import com.ey.dao.entity.PayAccountBill;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.PaymentCatv;
import com.ey.dao.entity.PaymentElectic;
import com.ey.dao.entity.PaymentFixedline;
import com.ey.dao.entity.PaymentGas;
import com.ey.dao.entity.PaymentHeating;
import com.ey.dao.entity.PaymentMobile;
import com.ey.dao.entity.PaymentProperty;
import com.ey.dao.entity.PaymentTraffic;
import com.ey.dao.entity.PaymentWater;
import com.ey.forms.JfForm;

public interface JfDAO extends BaseDAO {

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentWater paymentWater, BillModel bm);

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentElectic paymentElectic, BillModel bm);

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentFixedline paymentFixedline, BillModel bm);

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentGas paymentGas, BillModel bm);

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentProperty paymentProperty, BillModel bm);

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentCatv paymentCatv, BillModel bm);

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentMobile paymentMobile, BillModel bm);

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentTraffic paymentTraffic, BillModel bm);

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentHeating paymentHeating, BillModel bm);

	List<Object[]> getTotalRecords(Long userId,Integer year,
			String startMonth, String endMonth);

	List<PaymentBill> getDetails(Long userId, Integer year, Integer month);
	
	List<PaymentStatisBo> getPaymentStatisRecords(Long userId,Integer year,
			String startMonth, String endMonth) throws RuntimeException;

	public List<String> getPaymentItems(Long userId,Integer year,
			Integer month)throws RuntimeException;
	List<BillSubject> getBillSubjects(String sourceId);
	void saveBillSubjectData(JfForm form, PaymentBill paymentBill,
			PayAccountBill payAccountBill, Object formObject);
}
