package com.ey.bo;

public class PaymentSettingTrafficBo {

	private String billNumber;
	
	private String hoster;
	
	private String vehicleNumber;
	
	private String carframeNumber;
	
	private String engineNumber;
	
	private String areaId;
	
	private Long entId;
	
	private String entName;
	
	private String areaName;
	
	public PaymentSettingTrafficBo(){
		
	}
	
	public PaymentSettingTrafficBo(String areaId, Long entId, String entName, 
			String billNumber,String hoster, 
			String vehicleNumber, String carframeNumber, 
			String engineNumber, String areaName){
		this.areaId = areaId;
		this.entId = entId;
		this.entName = entName;
		this.billNumber = billNumber;
		this.hoster = hoster;
		this.vehicleNumber = vehicleNumber;
		this.carframeNumber = carframeNumber;
		this.engineNumber = engineNumber;
		this.areaName = areaName;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public String getHoster() {
		return hoster;
	}

	public void setHoster(String hoster) {
		this.hoster = hoster;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getCarframeNumber() {
		return carframeNumber;
	}

	public void setCarframeNumber(String carframeNumber) {
		this.carframeNumber = carframeNumber;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Long getEntId() {
		return entId;
	}

	public void setEntId(Long entId) {
		this.entId = entId;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
}
