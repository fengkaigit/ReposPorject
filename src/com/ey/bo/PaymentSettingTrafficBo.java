package com.ey.bo;

public class PaymentSettingTrafficBo {

	private String billNumber;
	
	private String hoster;
	
	private String vehicleNumber;
	
	private String carframeNumber;
	
	private String engineNumber;
	
	public PaymentSettingTrafficBo(){
		
	}
	
	public PaymentSettingTrafficBo(String billNumber,String hoster, 
			String vehicleNumber, String carframeNumber, String engineNumber){
		this.billNumber = billNumber;
		this.hoster = hoster;
		this.vehicleNumber = vehicleNumber;
		this.carframeNumber = carframeNumber;
		this.engineNumber = engineNumber;
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
	
}
