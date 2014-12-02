package com.ey.dao.impl;

import org.springframework.stereotype.Repository;

import com.ey.dao.JfDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
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
import com.ey.util.StringUtil;

@Repository("jfDAO")
public class JfDAOImpl extends BaseDAOImpl implements JfDAO {

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentWater paymentWater) {
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentWater.getId() == null) {
			paymentWater.setId(paymentBill.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(payAccountBill);
		save(paymentBill);
		save(paymentWater);
	}

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentElectic paymentElectic) {
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentElectic.getId() == null) {
			paymentElectic.setId(paymentBill.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(payAccountBill);
		save(paymentBill);
		save(paymentElectic);
	}

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentFixedline paymentFixedline) {
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentFixedline.getId() == null) {
			paymentFixedline.setId(paymentBill.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(payAccountBill);
		save(paymentBill);
		save(paymentFixedline);
	}

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentGas paymentGas) {
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentGas.getId() == null) {
			paymentGas.setId(paymentBill.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(payAccountBill);
		save(paymentBill);
		save(paymentGas);
	}

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentProperty paymentProperty) {
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentProperty.getId() == null) {
			paymentProperty.setId(paymentBill.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(payAccountBill);
		save(paymentBill);
		save(paymentProperty);
	}

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentCatv paymentCatv) {
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentCatv.getId() == null) {
			paymentCatv.setId(paymentBill.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(payAccountBill);
		save(paymentBill);
		save(paymentCatv);
	}

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentMobile paymentMobile) {
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentMobile.getId() == null) {
			paymentMobile.setId(paymentBill.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(payAccountBill);
		save(paymentBill);
		save(paymentMobile);

	}

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentTraffic paymentTraffic) {
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentTraffic.getId() == null) {
			paymentTraffic.setId(paymentBill.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(payAccountBill);
		save(paymentBill);
		save(paymentTraffic);

	}

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentHeating paymentHeating) {
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentHeating.getId() == null) {
			paymentHeating.setId(paymentBill.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(payAccountBill);
		save(paymentBill);
		save(paymentHeating);
	}

	@Override
	public java.util.List<Object[]> getTotalRecords(Long userId,Integer year,
			String startMonth, String endMonth) {
		int sm = 1;
		int em = 12;
		if (StringUtil.isEmptyString(startMonth)) {
			sm = 1;
		} else {
			sm = new Integer(startMonth);
		}
		if (StringUtil.isEmptyString(endMonth)) {
			em = 12;
		} else {
			em = new Integer(endMonth);
		}
		// SELECT
		// SUM(pay_money+poundage),SUM(pay_money),SUM(poundage),pay_type,payment_status,YEAR,MONTH
		// FROM payment_bill WHERE payment_status=1 OR payment_status=2 GROUP BY
		// YEAR,MONTH,pay_type,payment_status ORDER BY YEAR,month
		String hql = "select sum(payMoney+poundage),payType,paymentStatus,year,month from PaymentBill where userId=? and year=? and month>=? and month<=? and (paymentStatus=1 or paymentStatus=2) group by year,month,payType,paymentStatus order by year,month";
		return this.find(hql, new Object[]{userId,year,sm,em});
	}

	@Override
	public java.util.List<PaymentBill> getDetails(Long userId, Integer year,
			Integer month) {
		String hql = "from PaymentBill where year=? and month=? and userId=? and (paymentStatus=1 or paymentStatus=2) order by createTime";
		return this.find(hql, new Object[]{year,month,userId});
	}

}
