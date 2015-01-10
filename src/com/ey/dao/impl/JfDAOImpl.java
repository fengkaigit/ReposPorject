package com.ey.dao.impl;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ey.bo.PaymentStatisBo;
import com.ey.dao.JfDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.BillModel;
import com.ey.dao.entity.BillSubject;
import com.ey.dao.entity.BillSubjectData;
import com.ey.dao.entity.BillSubjectDataId;
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
import com.ey.util.ConvertUtil;
import com.ey.util.DateUtil;
import com.ey.util.StringUtil;

@Repository("jfDAO")
public class JfDAOImpl extends BaseDAOImpl implements JfDAO {

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentWater paymentWater,BillModel bm) {
		if (bm.getId() == null) {
			super.getDbId(bm);
		}
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
			payAccountBill.setModelId(bm.getId());
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentWater.getId() == null) {
			paymentWater.setId(paymentBill.getId());
			paymentWater.setModelId(bm.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(bm);
		save(payAccountBill);
		save(paymentBill);
		save(paymentWater);
	}

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentElectic paymentElectic, BillModel bm) {
		if (bm.getId() == null) {
			super.getDbId(bm);
		}
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
			payAccountBill.setModelId(bm.getId());
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentElectic.getId() == null) {
			paymentElectic.setId(paymentBill.getId());
			paymentElectic.setModelId(bm.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(bm);
		save(payAccountBill);
		save(paymentBill);
		save(paymentElectic);
	}

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentFixedline paymentFixedline, BillModel bm) {
		if (bm.getId() == null) {
			super.getDbId(bm);
		}
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
			payAccountBill.setModelId(bm.getId());
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentFixedline.getId() == null) {
			paymentFixedline.setId(paymentBill.getId());
			paymentFixedline.setModelId(bm.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(bm);
		save(payAccountBill);
		save(paymentBill);
		save(paymentFixedline);
	}

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentGas paymentGas, BillModel bm) {
		if (bm.getId() == null) {
			super.getDbId(bm);
		}
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
			payAccountBill.setModelId(bm.getId());
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentGas.getId() == null) {
			paymentGas.setId(paymentBill.getId());
			paymentGas.setModelId(bm.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(bm);
		save(payAccountBill);
		save(paymentBill);
		save(paymentGas);
	}

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentProperty paymentProperty, BillModel bm) {
		if (bm.getId() == null) {
			super.getDbId(bm);
		}
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
			payAccountBill.setModelId(bm.getId());
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentProperty.getId() == null) {
			paymentProperty.setId(paymentBill.getId());
			paymentProperty.setModelId(bm.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(bm);
		save(payAccountBill);
		save(paymentBill);
		save(paymentProperty);
	}

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentCatv paymentCatv, BillModel bm) {
		if (bm.getId() == null) {
			super.getDbId(bm);
		}
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
			payAccountBill.setModelId(bm.getId());
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentCatv.getId() == null) {
			paymentCatv.setId(paymentBill.getId());
			paymentCatv.setModelId(bm.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(bm);
		save(payAccountBill);
		save(paymentBill);
		save(paymentCatv);
	}

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentMobile paymentMobile, BillModel bm) {
		if (bm.getId() == null) {
			super.getDbId(bm);
		}
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
			payAccountBill.setModelId(bm.getId());
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentMobile.getId() == null) {
			paymentMobile.setId(paymentBill.getId());
			paymentMobile.setModelId(bm.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(bm);
		save(payAccountBill);
		save(paymentBill);
		save(paymentMobile);

	}

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentTraffic paymentTraffic, BillModel bm) {
		if (bm.getId() == null) {
			super.getDbId(bm);
		}
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
			payAccountBill.setModelId(bm.getId());
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentTraffic.getId() == null) {
			paymentTraffic.setId(paymentBill.getId());
			paymentTraffic.setModelId(bm.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(bm);
		save(payAccountBill);
		save(paymentBill);
		save(paymentTraffic);

	}

	@Override
	public void saveBill(PayAccountBill payAccountBill,
			PaymentBill paymentBill, PaymentHeating paymentHeating, BillModel bm) {
		if (bm.getId() == null) {
			super.getDbId(bm);
		}
		if (payAccountBill.getId() == null) {
			super.getDbId(payAccountBill);
			payAccountBill.setModelId(bm.getId());
		}
		if (paymentBill.getAccountBillId() == null) {
			paymentBill.setAccountBillId(payAccountBill.getId());
		}
		if (paymentBill.getId() == null) {
			super.getDbId(paymentBill);
		}
		if (paymentHeating.getId() == null) {
			paymentHeating.setId(paymentBill.getId());
			paymentHeating.setModelId(bm.getId());
		}
		if (paymentBill.getOrderNumber() == null) {
			paymentBill.setOrderNumber(StringUtil.getBillNo(paymentBill
					.getCreateTime(), paymentBill.getId()));
		}
		save(bm);
		save(payAccountBill);
		save(paymentBill);
		save(paymentHeating);
	}

	@Override
	public List<Object[]> getTotalRecords(Long userId,Integer year,
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
		String hql = "select sum(payMoney+poundage),payType,year,month from PaymentBill where userId=? and year=? and month>=? and month<=? and paymentStatus>0 group by year,month,payType order by year,month";
		return this.find(hql, new Object[]{userId,year,sm,em});
	}
	@Override
	public java.util.List<PaymentBill> getDetails(Long userId, Integer year,
			Integer month) {
		String hql = "from PaymentBill where year=? and month=? and userId=? and paymentStatus>0 order by createTime";
		return this.find(hql, new Object[]{year,month,userId});
	}

	@Override
	public List<PaymentStatisBo> getPaymentStatisRecords(Long userId,
			Integer year, String startMonth, String endMonth)
			throws RuntimeException {
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
		String hql = "select new com.ey.bo.PaymentStatisBo(year,month,sum(payMoney+poundage)) from PaymentBill where userId=? and year=? and month>=? and month<=? and paymentStatus>0  group by year,month order by year,month";
		return this.find(hql, new Object[]{userId,year,sm,em});
	}

	@Override
	public List<String> getPaymentItems(Long userId, Integer year,
			Integer month) throws RuntimeException {
		String hql = "select distinct b.propChName from PaymentBill a, BaseCustomValue b where a.userId=? and a.year=? and a.month=? and a.paymentStatus>0 " +
				" and a.payType = b.id.dataValue and b.id.customEngName='payment_type'";
		return this.find(hql, new Object[]{userId,year,month});
	}

	@Override
	public List<BillSubject> getBillSubjects(String sourceId) {
		// TODO Auto-generated method stub
		String hql = "from BillSubject where sourceId=? order by id";
		return find(hql,new Object[]{sourceId});
	}

	@Override
	public void saveBillSubjectData(JfForm form, PaymentBill paymentBill,
			PayAccountBill payAccountBill, Object formObject) {
		Integer ps = form.getPaymentStatus();
		if(ps==null||ps.intValue()==0){
			return;
		}
		List<BillSubject> subjects = getBillSubjects(form.getPayType().toString());
		Map<String,Object> map = new HashMap();
		ConvertUtil.copyProperties(map, formObject);
		List<BillSubjectData> bsds = new ArrayList();
		for(BillSubject subject:subjects){
			Object obj = map.get(subject.getFieldName());
			String data = null;
			if(obj!=null){
				if(obj instanceof Date){
					data = DateUtil.convertDateToString("yyyy-MM-dd HH:mm:ss", (Date)obj);
				}else{
					data = obj.toString();
				}
			}
			BillSubjectData bsd = new BillSubjectData(new BillSubjectDataId(form.getModelId(),subject.getId()),subject.getFieldName(),data);
			bsds.add(bsd);
		}
		
		Map<String,Object> otherMap = new HashMap();
		otherMap.put("clientType", form.getClientType());
		otherMap.put("orderNum", form.getOrderNum());
		otherMap.put("moneycn", form.getMoneycn());
		otherMap.put("billNo", form.getBillNo());
		otherMap.put("billDate", form.getBillDate());
		otherMap.put("billId", form.getBillId());
		otherMap.put("bankName", form.getBankName());
		otherMap.put("bankAccount", form.getBankAccount());
		otherMap.put("totalMoney", form.getTotalMoney());
		otherMap.put("bankCode", form.getBankCode());
		otherMap.put("paymentbillid", paymentBill.getId());
		otherMap.put("paymentaccountid", payAccountBill.getId());
		otherMap.put("payuserid", form.getUserId());
		otherMap.put("payusername", form.getUserName());
		
		if(!StringUtil.isEmptyString(form.getJfdate())&&form.getJfmonth()!=null){
			otherMap.put("jfmonth", form.getJfmonth());
			otherMap.put("jfdate", form.getJfdate());
			if(!StringUtil.isEmptyString(form.getTvgroup())){
				String[] strs = form.getTvgroup().split(",");
				if(strs.length==3){
					otherMap.put("tvmid", strs[0]);
					otherMap.put("tvmname", strs[1]);
					otherMap.put("tvmmoney", strs[2]);
				}
			}
			if(!StringUtil.isEmptyString(form.getOthertvs())){
				String[] strs = form.getOthertvs().split(";");
				int i=0;
				for(String str:strs){
					String[] ses = str.split(",");
					if(ses.length==3){
						otherMap.put("tvoid"+i, ses[0]);
						otherMap.put("tvoname"+i, ses[1]);
						otherMap.put("tvomoney"+i, ses[2]);
					}
					i++;
				}
			}
		}
		if(form.getMobiles()!=null&&form.getBillMoneys()!=null){
			for(int i=0;i<form.getMobiles().length;i++){
				String mobile = form.getMobiles()[i];
				double money = form.getBillMoneys()[i];
				otherMap.put("mobile"+i, mobile);
				otherMap.put("mobilemoney"+i, money);
			}
		}
		Set<String> sets = otherMap.keySet();
		int i=1;
		for(String set:sets){
			Object obj = otherMap.get(set);
			String data = null;
			if(obj!=null){
				if(obj instanceof Date){
					data = DateUtil.convertDateToString("yyyy-MM-dd HH:mm:ss", (Date)obj);
				}else{
					data = obj.toString();
				}
			}
			BillSubjectData bsd = new BillSubjectData(new BillSubjectDataId(form.getModelId(),new Long(0-i)),set,data);
			bsds.add(bsd);
			i++;
		}
		if(bsds.size()>0){
			this.batchSaveVO(bsds);
		}
	}
}
