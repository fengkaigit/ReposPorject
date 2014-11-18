package com.ey.bo;

import com.ey.dao.entity.ChargeEnterprise;

public class ChargeEntBo extends ChargeEnterprise {
     private String payTypeName;
     private String areaName;
     
	public String getPayTypeName() {
		return payTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public ChargeEntBo(Long id, String areaId, String enterpriseName,
			String careNumber, Integer payType,String areaName) {
		super(id, areaId, enterpriseName, careNumber, payType);
		this.areaName = areaName;
	}
     
     
}
