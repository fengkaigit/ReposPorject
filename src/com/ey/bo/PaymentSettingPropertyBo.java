package com.ey.bo;

public class PaymentSettingPropertyBo {

	private String hoster;
	
	private String payAddress;
	
	public PaymentSettingPropertyBo(){
		
	}
	
	public PaymentSettingPropertyBo(String hoster, String payAddress){
		this.hoster = hoster;
		this.payAddress = payAddress;
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
