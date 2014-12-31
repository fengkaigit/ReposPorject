package com.ey.dao;

import java.util.List;

import com.ey.bo.PaymentStatisBo;
import com.ey.dao.base.BaseDAO;
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

public interface JfDAO extends BaseDAO {

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentWater paymentWater);

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentElectic paymentElectic);

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentFixedline paymentFixedline);

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentGas paymentGas);

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentProperty paymentProperty);

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentCatv paymentCatv);

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentMobile paymentMobile);

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentTraffic paymentTraffic);

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentHeating paymentHeating);

	List<Object[]> getTotalRecords(Long userId,Integer year,
			String startMonth, String endMonth);

	List<PaymentBill> getDetails(Long userId, Integer year, Integer month);
	
	List<PaymentStatisBo> getPaymentStatisRecords(Long userId,Integer year,
			String startMonth, String endMonth) throws RuntimeException;

	public List<String> getPaymentItems(Long userId,Integer year,
			Integer month)throws RuntimeException;
}
