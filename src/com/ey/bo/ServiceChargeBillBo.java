package com.ey.bo;

import java.util.Date;

import com.ey.dao.entity.ServiceChargeBill;

public class ServiceChargeBillBo extends ServiceChargeBill {

	private String strStatus;
	
	public ServiceChargeBillBo(){
		
	}
	
	public ServiceChargeBillBo(Long id, Date createDate, Date confirmDate,
			double profitMoney, Integer status, String strStatus) {
		super(id,createDate,confirmDate,profitMoney,status);
		this.strStatus=strStatus;
	}

	public String getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	
	
}
