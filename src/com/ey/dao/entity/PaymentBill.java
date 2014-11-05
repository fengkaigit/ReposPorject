package com.ey.dao.entity;

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
@Entity
@Table(name = "payment_bill")
public class PaymentBill implements java.io.Serializable {

	// Fields

	private long id;
	private long accountBillId;
	private long userId;
	private double remainBalance;
	private Date createTime;
	private double paidMoney;
	private double payMoney;
	private double balance;
	private double poundage;
	private Integer payType;
	private long entId;
	private Integer businessType;
	private Integer paymentStatus;
	private Integer paymentMode;

	// Constructors

	/** default constructor */
	public PaymentBill() {
	}

	/** minimal constructor */
	public PaymentBill(long id, long accountBillId, long userId,
			double remainBalance, Date createTime, double paidMoney,
			double balance, Integer payType, long entId, Integer businessType,
			Integer paymentMode) {
		this.id = id;
		this.accountBillId = accountBillId;
		this.userId = userId;
		this.remainBalance = remainBalance;
		this.createTime = createTime;
		this.paidMoney = paidMoney;
		this.balance = balance;
		this.payType = payType;
		this.entId = entId;
		this.businessType = businessType;
		this.paymentMode = paymentMode;
	}

	/** full constructor */
	public PaymentBill(long id, long accountBillId, long userId,
			double remainBalance, Date createTime, double paidMoney,
			double payMoney, double balance, double poundage, Integer payType,
			long entId, Integer businessType, Integer paymentStatus,
			Integer paymentMode) {
		this.id = id;
		this.accountBillId = accountBillId;
		this.userId = userId;
		this.remainBalance = remainBalance;
		this.createTime = createTime;
		this.paidMoney = paidMoney;
		this.payMoney = payMoney;
		this.balance = balance;
		this.poundage = poundage;
		this.payType = payType;
		this.entId = entId;
		this.businessType = businessType;
		this.paymentStatus = paymentStatus;
		this.paymentMode = paymentMode;
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

	@Column(name = "account_bill_id", nullable = false)
	public long getAccountBillId() {
		return this.accountBillId;
	}

	public void setAccountBillId(long accountBillId) {
		this.accountBillId = accountBillId;
	}

	@Column(name = "user_id", nullable = false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "remain_balance", nullable = false, precision = 22, scale = 0)
	public double getRemainBalance() {
		return this.remainBalance;
	}

	public void setRemainBalance(double remainBalance) {
		this.remainBalance = remainBalance;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "create_time", nullable = false, length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "paid_money", nullable = false, precision = 22, scale = 0)
	public double getPaidMoney() {
		return this.paidMoney;
	}

	public void setPaidMoney(double paidMoney) {
		this.paidMoney = paidMoney;
	}

	@Column(name = "pay_money", precision = 22, scale = 0)
	public double getPayMoney() {
		return this.payMoney;
	}

	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}

	@Column(name = "balance", nullable = false, precision = 22, scale = 0)
	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Column(name = "poundage", precision = 22, scale = 0)
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

	@Column(name = "payment_status")
	public Integer getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Column(name = "payment_mode", nullable = false)
	public Integer getPaymentMode() {
		return this.paymentMode;
	}

	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}

}