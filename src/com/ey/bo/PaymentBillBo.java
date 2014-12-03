package com.ey.bo;
import java.util.Date;

import com.ey.dao.entity.PaymentBill;

public class PaymentBillBo extends PaymentBill {

	private String entName; 
	
	private String userName;
	
	private String paymentTypeName;
	
	public PaymentBillBo(){
		
	}
	
	public PaymentBillBo(Long id, Long accountBillId, Long userId,
			double remainBalance, Date createTime, double paidMoney,
			double payMoney, double balance, double poundage, Integer payType,
			Long entId, Integer businessType, Integer paymentStatus,
			Integer paymentMode, String uuid, Integer divideStatus, 
			String areaId, String areaName, Long agentId, String agentName,
			String orderNumber, String remarks, String payAddress, 
			String entName, String userName, String paymentTypeName) {
		super(id, accountBillId, userId,remainBalance, createTime, paidMoney,
				payMoney, balance, poundage, payType,
				entId, businessType, paymentStatus,
				paymentMode, uuid, divideStatus, 
				areaId, areaName, agentId, agentName,
				orderNumber, remarks, payAddress);
		this.entName = entName;
		this.userName = userName;
		this.paymentTypeName = paymentTypeName;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}
	
	
}
