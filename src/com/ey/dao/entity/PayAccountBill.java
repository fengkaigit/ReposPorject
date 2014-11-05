package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PayAccountBill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pay_account_bill")
public class PayAccountBill implements java.io.Serializable {

	// Fields

	private long id;
	private long userId;
	private Date createTime;
	private long modelId;
	private double billMoney;
	private double poundage;
	private Integer payType;
	private Integer billType;
	private long entId;
	private Integer businessType;

	// Constructors

	/** default constructor */
	public PayAccountBill() {
	}

	/** full constructor */
	public PayAccountBill(long id, long userId, Date createTime, long modelId,
			double billMoney, double poundage, Integer payType,
			Integer billType, long entId, Integer businessType) {
		this.id = id;
		this.userId = userId;
		this.createTime = createTime;
		this.modelId = modelId;
		this.billMoney = billMoney;
		this.poundage = poundage;
		this.payType = payType;
		this.billType = billType;
		this.entId = entId;
		this.businessType = businessType;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "user_id", nullable = false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "create_time", nullable = false, length = 8)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "model_id", nullable = false)
	public long getModelId() {
		return this.modelId;
	}

	public void setModelId(long modelId) {
		this.modelId = modelId;
	}

	@Column(name = "bill_money", nullable = false, precision = 22, scale = 0)
	public double getBillMoney() {
		return this.billMoney;
	}

	public void setBillMoney(double billMoney) {
		this.billMoney = billMoney;
	}

	@Column(name = "poundage", nullable = false, precision = 22, scale = 0)
	public double getPoundage() {
		return this.poundage;
	}

	public void setPoundage(double poundage) {
		this.poundage = poundage;
	}

	@Column(name = "pay_type", nullable = false)
	public Integer getPayType() {
		return this.payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	@Column(name = "bill_type", nullable = false)
	public Integer getBillType() {
		return this.billType;
	}

	public void setBillType(Integer billType) {
		this.billType = billType;
	}

	@Column(name = "ent_id", nullable = false)
	public long getEntId() {
		return this.entId;
	}

	public void setEntId(long entId) {
		this.entId = entId;
	}

	@Column(name = "business_type", nullable = false)
	public Integer getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

}