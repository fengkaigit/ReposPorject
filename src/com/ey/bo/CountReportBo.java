package com.ey.bo;

import java.io.Serializable;

public class CountReportBo implements Serializable {
	
	private String month;
	private String userNum;
	private String userPercent;
	private String billMoney;
	private String billPercent;
	
    public CountReportBo(){
    	
    }

	public CountReportBo(String month, String userNum, String userPercent,
			String billMoney, String billPercent) {
		super();
		this.month = month;
		this.userNum = userNum;
		this.userPercent = userPercent;
		this.billMoney = billMoney;
		this.billPercent = billPercent;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getUserPercent() {
		return userPercent;
	}

	public void setUserPercent(String userPercent) {
		this.userPercent = userPercent;
	}

	public String getBillMoney() {
		return billMoney;
	}

	public void setBillMoney(String billMoney) {
		this.billMoney = billMoney;
	}

	public String getBillPercent() {
		return billPercent;
	}

	public void setBillPercent(String billPercent) {
		this.billPercent = billPercent;
	}
    
}
