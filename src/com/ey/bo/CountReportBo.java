package com.ey.bo;

import java.io.Serializable;

public class CountReportBo implements Serializable {
	
	private String month;
	private Long userNum;
	private Double userPercent;
	private Double billMoney;
	private Double billPercent;
	
    public CountReportBo(){
    	
    }

	public CountReportBo(String month, Long userNum, Double userPercent,
			Double billMoney, Double billPercent) {
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

	public Long getUserNum() {
		return userNum;
	}

	public void setUserNum(Long userNum) {
		this.userNum = userNum;
	}

	public Double getUserPercent() {
		return userPercent;
	}

	public void setUserPercent(Double userPercent) {
		this.userPercent = userPercent;
	}

	public Double getBillMoney() {
		return billMoney;
	}

	public void setBillMoney(Double billMoney) {
		this.billMoney = billMoney;
	}

	public Double getBillPercent() {
		return billPercent;
	}

	public void setBillPercent(Double billPercent) {
		this.billPercent = billPercent;
	}
    
}
