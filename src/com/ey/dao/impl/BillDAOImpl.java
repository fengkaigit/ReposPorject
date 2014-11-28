package com.ey.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.bo.PaymentBillBo;
import com.ey.bo.ServiceChargeBillBo;
import com.ey.dao.BillDAO;

import com.ey.dao.base.impl.BaseDAOImpl;

@Repository("billDao")
public class BillDAOImpl extends BaseDAOImpl implements BillDAO {

	@Override
	public List<ServiceChargeBillBo> getServiceBillList()
			throws RuntimeException {
		String hql = "select new com.ey.bo.ServiceChargeBillBo(a.id,a.createDate,a.confirmDate,a.profitMoney,a.status,b.propChName) from ServiceChargeBill a,BaseCustomValue b where b.id.customEngName='transfer_bill_status' and a.status=b.id.dataValue ";
		List<ServiceChargeBillBo> lst = this.find(hql);
		return lst;
	}

	@Override
	public List<PaymentBillBo> getPaymentBillList(Long serviceBillId)
			throws RuntimeException {
		String hql = "select new com.ey.bo.PaymentBillBo(a.id, a.accountBillId, a.userId,a.remainBalance, " +
				"a.createTime, a.paidMoney,a.payMoney, a.balance, a.poundage, a.payType,a.entId, a.businessType, " +
				"a.paymentStatus,a.paymentMode, a.uuid, a.divideStatus, a.areaId, a.areaName, a.agentId, " +
				"a.agentName,a.orderNumber, a.remarks, a.payAddress,b.enterpriseName,c.realName,d.propChName) " +
				" from PaymentBill a,ChargeEnterprise b,UserBase c,BaseCustomValue d,ServicePaymentRelation e " +
				" where d.id.customEngName='payment_type' and a.payType=d.id.dataValue and a.entId=b.id and a.userId=c.id" +
				" and a.id=e.id.paymentBillId and e.id.serviceBillId=?";
		List<PaymentBillBo> lst = this.find(hql,new Object[]{serviceBillId});
		return lst;
	}

	

}
