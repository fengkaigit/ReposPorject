package com.ey.bo;

public class PaymentStatisBo implements java.io.Serializable {
	
	private Double money;// 金额
	private String payType;// 缴费类型
	private String payStatus;// 状态
	private Integer year;// 年
	private Integer month;// 月
	private String item;//统计缴费类型
	private String payDate;//缴费日期
	
	public PaymentStatisBo() {
		super();
	}
	
	public PaymentStatisBo(Integer year, Integer month, Double money){
		this.year = year;
		this.money = money;
		this.month = month;
	}
	
	public PaymentStatisBo(Integer year, Integer month, Double money, String payType, String payStatus, String payDate){
		this.year = year;
		this.money = money;
		this.month = month;
		this.payType = payType;
		this.payStatus = payStatus;
		this.payDate = payDate;
	}
	
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	
	
}
