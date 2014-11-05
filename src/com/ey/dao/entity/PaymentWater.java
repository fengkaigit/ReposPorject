package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PaymentWater entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "payment_water")
public class PaymentWater implements java.io.Serializable {

	// Fields

	private long id;
	private long userId;
	private long modelId;
	private Date beginPeriod;
	private Date endPeriod;
	private String periodFrequency;
	private double billMoney;
	private double poundage;
	private Date paymentTime;
	private String billNumber;

	// Constructors

	/** default constructor */
	public PaymentWater() {
	}

	/** minimal constructor */
	public PaymentWater(long id, long userId, Date beginPeriod,
			String periodFrequency, double billMoney, Date paymentTime,
			String billNumber) {
		this.id = id;
		this.userId = userId;
		this.beginPeriod = beginPeriod;
		this.periodFrequency = periodFrequency;
		this.billMoney = billMoney;
		this.paymentTime = paymentTime;
		this.billNumber = billNumber;
	}

	/** full constructor */
	public PaymentWater(long id, long userId, long modelId, Date beginPeriod,
			Date endPeriod, String periodFrequency, double billMoney,
			double poundage, Date paymentTime, String billNumber) {
		this.id = id;
		this.userId = userId;
		this.modelId = modelId;
		this.beginPeriod = beginPeriod;
		this.endPeriod = endPeriod;
		this.periodFrequency = periodFrequency;
		this.billMoney = billMoney;
		this.poundage = poundage;
		this.paymentTime = paymentTime;
		this.billNumber = billNumber;
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

	@Column(name = "user_ID", nullable = false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "model_id")
	public long getModelId() {
		return this.modelId;
	}

	public void setModelId(long modelId) {
		this.modelId = modelId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "begin_period", nullable = false, length = 10)
	public Date getBeginPeriod() {
		return this.beginPeriod;
	}

	public void setBeginPeriod(Date beginPeriod) {
		this.beginPeriod = beginPeriod;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_period", length = 10)
	public Date getEndPeriod() {
		return this.endPeriod;
	}

	public void setEndPeriod(Date endPeriod) {
		this.endPeriod = endPeriod;
	}

	@Column(name = "period_frequency", nullable = false, length = 20)
	public String getPeriodFrequency() {
		return this.periodFrequency;
	}

	public void setPeriodFrequency(String periodFrequency) {
		this.periodFrequency = periodFrequency;
	}

	@Column(name = "bill_money", nullable = false, precision = 22, scale = 0)
	public double getBillMoney() {
		return this.billMoney;
	}

	public void setBillMoney(double billMoney) {
		this.billMoney = billMoney;
	}

	@Column(name = "poundage", precision = 22, scale = 0)
	public double getPoundage() {
		return this.poundage;
	}

	public void setPoundage(double poundage) {
		this.poundage = poundage;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "payment_time", nullable = false, length = 10)
	public Date getPaymentTime() {
		return this.paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	@Column(name = "bill_number", nullable = false, length = 30)
	public String getBillNumber() {
		return this.billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

}