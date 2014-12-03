package com.ey.bo;

public class PaymentSettingPhoneBo {

	private String billNumber;
	
	private String hoster;
	
	public PaymentSettingPhoneBo(){
		
	}
	
	public PaymentSettingPhoneBo(String billNumber,String hoster){
		this.billNumber = billNumber;
		this.hoster = hoster;
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

}
