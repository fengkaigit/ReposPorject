package com.ey.bo;

import com.ey.dao.entity.ChargeEnterprise;

public class ChargeEntBo extends ChargeEnterprise {
     private String payTypeName;
     private String areaName;
     private String areaPathName;
     private String areaPath;
     
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
			String careNumber, Integer payType,byte[] exPic,String areaName,String areaPathName,String areaPath,String payTypeName ) {
		super(id, areaId, enterpriseName, careNumber, payType,exPic);
		this.areaName = areaName;
		this.areaPathName = areaPathName.substring(9);
		this.areaPath = areaPath.substring(8);
		this.payTypeName = payTypeName;
		
	}
	public ChargeEntBo(Long id, String areaId, String enterpriseName,
			String careNumber, Integer payType,byte[] exPic,String areaName,String areaPathName,String areaPath ) {
		super(id, areaId, enterpriseName, careNumber, payType,exPic);
		this.areaName = areaName;
		this.areaPathName = areaPathName.substring(9);
		this.areaPath = areaPath.substring(8);
		
	}
	public String getAreaPathName() {
		return areaPathName;
	}
	public void setAreaPathName(String areaPathName) {
		this.areaPathName = areaPathName;
	}
	public String getAreaPath() {
		return areaPath;
	}
	public void setAreaPath(String areaPath) {
		this.areaPath = areaPath;
	}
    
     
}
