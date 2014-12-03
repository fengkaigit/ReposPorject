package com.ey.bo;

public class PaymentSettingStandardBo {

	private String billNumber;
	
	private String hoster;
	
	private String payAddress;
	
	public PaymentSettingStandardBo(){
		
	}
	
	public PaymentSettingStandardBo(String billNumber,String hoster, String payAddress){
		this.billNumber = billNumber;
		this.hoster = hoster;
		this.payAddress = payAddress;
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
	
	
}
