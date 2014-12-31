package com.ey.bo;

public class PaymentSettingStandardBo {

	private String billNumber;
	
	private String hoster;
	
	private String payAddress;
	
	private String areaId;
	
	private Long entId;
	
	private String entName;
	
	private String areaName;
	
	public PaymentSettingStandardBo(){
		
	}
	
	public PaymentSettingStandardBo(String areaId, Long entId, String entName,
			String billNumber,String hoster, String payAddress, String areaName){
		this.areaId = areaId;
		this.entId = entId;
		this.entName = entName;
		this.billNumber = billNumber;
		this.hoster = hoster;
		this.payAddress = payAddress;
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

	public String getPayAddress() {
		return payAddress;
	}

	public void setPayAddress(String payAddress) {
		this.payAddress = payAddress;
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
