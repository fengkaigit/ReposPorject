package com.ey.dao;

import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.PayAccountBill;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.PaymentWater;

public interface JfDAO extends BaseDAO {

	void saveBill(PayAccountBill payAccountBill, PaymentBill paymentBill,
			PaymentWater paymentWater);

}
