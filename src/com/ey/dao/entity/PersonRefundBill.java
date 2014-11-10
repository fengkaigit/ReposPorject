package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PersonRefundBill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "person_refund_bill")
public class PersonRefundBill implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private Date createTime;
	private Date confirmTime;
	private double refundMoney;
	private Integer refundStatus;
	private Long paymentBillId;

	// Constructors

	/** default constructor */
	public PersonRefundBill() {
	}

	/** minimal constructor */
	public PersonRefundBill(Long id, Long userId, Date createTime,
			double refundMoney, Integer refundStatus, Long paymentBillId) {
		this.id = id;
		this.userId = userId;
		this.createTime = createTime;
		this.refundMoney = refundMoney;
		this.refundStatus = refundStatus;
		this.paymentBillId = paymentBillId;
	}

	/** full constructor */
	public PersonRefundBill(Long id, Long userId, Date createTime,
			Date confirmTime, double refundMoney, Integer refundStatus,
			Long paymentBillId) {
		this.id = id;
		this.userId = userId;
		this.createTime = createTime;
		this.confirmTime = confirmTime;
		this.refundMoney = refundMoney;
		this.refundStatus = refundStatus;
		this.paymentBillId = paymentBillId;
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

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "create_time", nullable = false, length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "confirm_time", length = 10)
	public Date getConfirmTime() {
		return this.confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	@Column(name = "refund_money", nullable = false, precision = 22, scale = 0)
	public double getRefundMoney() {
		return this.refundMoney;
	}

	public void setRefundMoney(double refundMoney) {
		this.refundMoney = refundMoney;
	}

	@Column(name = "refund_status", nullable = false)
	public Integer getRefundStatus() {
		return this.refundStatus;
	}

	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	@Column(name = "payment_bill_id", nullable = false)
	public Long getPaymentBillId() {
		return this.paymentBillId;
	}

	public void setPaymentBillId(Long paymentBillId) {
		this.paymentBillId = paymentBillId;
	}

}