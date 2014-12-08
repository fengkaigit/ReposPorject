package com.ey.bo;

public class ConfirmPaymentBo {

	private String orderNum;
	
	private int payType;
	
	private String billNumber;
	
	private String payData;
	
	private Double payMoney;
	
	private Double poundage;
	
	public ConfirmPaymentBo(){
		
	}
	
	public ConfirmPaymentBo(String orderNum, int payType, String billNumber,
			String payData, Double payMoney, Double poundage){
		this.orderNum = orderNum;
		this.payType = payType;
		this.billNumber = billNumber;
		this.payData = payData;
		this.payMoney = payMoney;
		this.poundage = poundage;
	}
	

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public String getPayData() {
		return payData;
	}

	public void setPayData(String payData) {
		this.payData = payData;
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public Double getPoundage() {
		return poundage;
	}

	public void setPoundage(Double poundage) {
		this.poundage = poundage;
	}
	
	
}
