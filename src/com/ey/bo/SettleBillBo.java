package com.ey.bo;

import java.util.Date;

import com.ey.dao.entity.SettleBill;

public class SettleBillBo extends SettleBill {

	private String agentName;
	
	private String strStatus;
	
	public SettleBillBo(){
		
	}
	
	public SettleBillBo(Long id, Date createDate, Date confirmDate,
			Double profitMoney, Integer status, Long agentId, String agentName, String strStatus){
		super(id, createDate, confirmDate, profitMoney, status, agentId);
		this.agentName = agentName;
		this.strStatus = strStatus;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	
	
}
