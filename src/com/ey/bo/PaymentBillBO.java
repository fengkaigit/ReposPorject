package com.ey.bo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PaymentBill entity. @author MyEclipse Persistence Tools
 */

public class PaymentBillBO implements java.io.Serializable {
	private Double money;// 金额
	private Integer payType;// 缴费类型
	private Integer payStatus;// 状态
	private Integer year;// 年
	private Integer month;// 月
	private String item;
	private Double sucessMoney;
	private Double faultMoney;
	private Integer totalNum;
	private Integer sucessNum;
	private Integer faultNum;
	
	private String payTime;
	
	private String payStatuStr;
	private String payTypeStr;
	private Date createTime;
	private Integer businessId;
	private String business;
	private Integer payModeId;
	private String payMode;

	public PaymentBillBO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentBillBO(Double money, Integer payType, Integer payStatus,
			Integer year, Integer month, String item, Double sucessMoney,
			Double faultMoney, Integer totalNum, Integer sucessNum,
			Integer faultNum) {
		super();
		this.money = money;
		this.payType = payType;
		this.payStatus = payStatus;
		this.year = year;
		this.month = month;
		this.item = item;
		this.sucessMoney = sucessMoney;
		this.faultMoney = faultMoney;
		this.totalNum = totalNum;
		this.sucessNum = sucessNum;
		this.faultNum = faultNum;
	}

	public PaymentBillBO(Integer payType, Integer payStatus,
			Integer year, Integer month,Double money, Double sucessMoney,
			Integer businessId, Integer payModeId,Date createTime) {
		super();
		this.money = money;
		this.payType = payType;
		this.payStatus = payStatus;
		this.year = year;
		this.month = month;
		this.sucessMoney = sucessMoney;
		this.businessId = businessId;
		this.payModeId = payModeId;
		this.createTime = createTime;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
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

	public Double getSucessMoney() {
		return sucessMoney;
	}

	public void setSucessMoney(Double sucessMoney) {
		this.sucessMoney = sucessMoney;
	}

	public Double getFaultMoney() {
		return faultMoney;
	}

	public void setFaultMoney(Double faultMoney) {
		this.faultMoney = faultMoney;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getSucessNum() {
		return sucessNum;
	}

	public void setSucessNum(Integer sucessNum) {
		this.sucessNum = sucessNum;
	}

	public Integer getFaultNum() {
		return faultNum;
	}

	public void setFaultNum(Integer faultNum) {
		this.faultNum = faultNum;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	

	public String getPayStatuStr() {
		return payStatuStr;
	}

	public void setPayStatuStr(String payStatuStr) {
		this.payStatuStr = payStatuStr;
	}

	public String getPayTypeStr() {
		return payTypeStr;
	}

	public void setPayTypeStr(String payTypeStr) {
		this.payTypeStr = payTypeStr;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public Integer getPayModeId() {
		return payModeId;
	}

	public void setPayModeId(Integer payModeId) {
		this.payModeId = payModeId;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

}