package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TempPaymentBill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "temp_payment_bill")
public class TempPaymentBill implements java.io.Serializable {

	// Fields

	private Long id;
	private Long accountBillId;
	private Long userId;
	private double remainBalance;
	private Date createTime;
	private double paidMoney;
	private double payMoney;
	private double balance;
	private double poundage;
	private Integer payType;
	private Long entId;
	private Integer businessType;
	private Integer paymentStatus;
	private Integer paymentMode;
	private String uuid;
	private Integer stageStatus;

	// Constructors

	/** default constructor */
	public TempPaymentBill() {
	}

	/** minimal constructor */
	public TempPaymentBill(Long id, Long accountBillId, Long userId,
			double remainBalance, Date createTime, double paidMoney,
			double balance, Integer payType, Long entId, Integer businessType,
			Integer paymentMode, String uuid, Integer stageStatus) {
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
		this.uuid = uuid;
		this.stageStatus = stageStatus;
	}

	/** full constructor */
	public TempPaymentBill(Long id, Long accountBillId, Long userId,
			double remainBalance, Date createTime, double paidMoney,
			double payMoney, double balance, double poundage, Integer payType,
			Long entId, Integer businessType, Integer paymentStatus,
			Integer paymentMode, String uuid, Integer stageStatus) {
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
		this.uuid = uuid;
		this.stageStatus = stageStatus;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "account_bill_id", nullable = false)
	public Long getAccountBillId() {
		return this.accountBillId;
	}

	public void setAccountBillId(Long accountBillId) {
		this.accountBillId = accountBillId;
	}

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
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
	public Long getEntId() {
		return this.entId;
	}

	public void setEntId(Long entId) {
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

	@Column(name = "uuid", nullable = false, length = 50)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "stage_status", nullable = false)
	public Integer getStageStatus() {
		return this.stageStatus;
	}

	public void setStageStatus(Integer stageStatus) {
		this.stageStatus = stageStatus;
	}

}