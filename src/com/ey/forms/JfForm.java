package com.ey.forms;

import java.io.Serializable;

public class JfForm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long billId;
	private String billNo;
	private String parentAreaId;
	private String areaId;
	private Long entId;
	private String billNumber;
	private Boolean bczh;
	private Integer year;
	private String month;
	private double billMoney;
	private double poundage;
	private String verify;
	private Integer payType;
	private Integer billType;
	private Integer businessType;
	private Integer paymentStatus;
	private String bankCode;
	private String bankName;
	private String bankAccount;
	private Long userId;
	private Integer paymentMode;
	private Integer divideStatus;
	private String periodFrequency; 
	private String endName;
	private String billDate;
	private String remark;
	private String mobileVerify;
	private String mobile;
	private String moneycn;
	private String areaName;
	private Long agentId;
	private String agentName;
	private String payAddress;
	private String[] mobiles;
	private String carOwner;
	private Integer carType;
	private String carframeNumber;
	private String engineNumber;
	private String vehicleNumber;
	private double[] billMoneys;
	private String userName;
	private String orderNum;
	private String othertvs;
	private Integer jfmonth;
	private String tvgroup;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getParentAreaId() {
		return parentAreaId;
	}
	public void setParentAreaId(String parentAreaId) {
		this.parentAreaId = parentAreaId;
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
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public Boolean getBczh() {
		return bczh;
	}
	public void setBczh(Boolean bczh) {
		this.bczh = bczh;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public double getBillMoney() {
		return billMoney;
	}
	public void setBillMoney(double billMoney) {
		this.billMoney = billMoney;
	}
	public double getPoundage() {
		return poundage;
	}
	public void setPoundage(double poundage) {
		this.poundage = poundage;
	}
	public String getVerify() {
		return verify;
	}
	public void setVerify(String verify) {
		this.verify = verify;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getBillType() {
		return billType;
	}
	public void setBillType(Integer billType) {
		this.billType = billType;
	}
	public Integer getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	public Integer getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId = billId;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}
	public Integer getDivideStatus() {
		return divideStatus;
	}
	public void setDivideStatus(Integer divideStatus) {
		this.divideStatus = divideStatus;
	}
	public String getPeriodFrequency() {
		return periodFrequency;
	}
	public void setPeriodFrequency(String periodFrequency) {
		this.periodFrequency = periodFrequency;
	}
	public String getEndName() {
		return endName;
	}
	public void setEndName(String endName) {
		this.endName = endName;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBillNo() {
		
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMobileVerify() {
		return mobileVerify;
	}
	public void setMobileVerify(String mobileVerify) {
		this.mobileVerify = mobileVerify;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMoneycn() {
		return moneycn;
	}
	public void setMoneycn(String moneycn) {
		this.moneycn = moneycn;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Long getAgentId() {
		return agentId;
	}
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getPayAddress() {
		return payAddress;
	}
	public void setPayAddress(String payAddress) {
		this.payAddress = payAddress;
	}
	public String[] getMobiles() {
		return mobiles;
	}
	public void setMobiles(String[] mobiles) {
		this.mobiles = mobiles;
	}
	public String getCarOwner() {
		return carOwner;
	}
	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}
	public Integer getCarType() {
		return carType;
	}
	public void setCarType(Integer carType) {
		this.carType = carType;
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
	public double[] getBillMoneys() {
		return billMoneys;
	}
	public void setBillMoneys(double[] billMoneys) {
		this.billMoneys = billMoneys;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getOthertvs() {
		return othertvs;
	}
	public void setOthertvs(String othertvs) {
		this.othertvs = othertvs;
	}
	public Integer getJfmonth() {
		return jfmonth;
	}
	public void setJfmonth(Integer jfmonth) {
		this.jfmonth = jfmonth;
	}
	public String getTvgroup() {
		return tvgroup;
	}
	public void setTvgroup(String tvgroup) {
		this.tvgroup = tvgroup;
	}
	
}
