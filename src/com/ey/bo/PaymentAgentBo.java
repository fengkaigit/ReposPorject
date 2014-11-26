package com.ey.bo;

import com.ey.dao.entity.AgentInfo;

public class PaymentAgentBo extends AgentInfo {
	private double serviceFee;
	private Long billId;
	private Long accountId;

	public PaymentAgentBo() {

	}

	public PaymentAgentBo(Long id, String registAccount, String passwd, String EMail,
			String mobile, double rebackDot, String registRealName, String areaId, double serviceFee) {
		super(id, registAccount, passwd, EMail, mobile, areaId, rebackDot,
				registRealName);
		this.serviceFee = serviceFee;
	}

	public double getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	
}
