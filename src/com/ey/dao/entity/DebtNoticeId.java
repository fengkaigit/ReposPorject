package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DebtNoticeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class DebtNoticeId implements java.io.Serializable {

	// Fields

	private Long batchId;
	private Long paymentId;
	private double billMoney;
	private double payMoney;
	private double debtMoney;
	private Integer noticeStatus;

	// Constructors

	/** default constructor */
	public DebtNoticeId() {
	}

	/** full constructor */
	public DebtNoticeId(Long batchId, Long paymentId, double billMoney,
			double payMoney, double debtMoney, Integer noticeStatus) {
		this.batchId = batchId;
		this.paymentId = paymentId;
		this.billMoney = billMoney;
		this.payMoney = payMoney;
		this.debtMoney = debtMoney;
		this.noticeStatus = noticeStatus;
	}

	// Property accessors

	@Column(name = "batch_id", nullable = false)
	public Long getBatchId() {
		return this.batchId;
	}

	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	@Column(name = "payment_id", nullable = false)
	public Long getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	@Column(name = "bill_money", nullable = false, precision = 22, scale = 0)
	public double getBillMoney() {
		return this.billMoney;
	}

	public void setBillMoney(double billMoney) {
		this.billMoney = billMoney;
	}

	@Column(name = "pay_money", nullable = false, precision = 22, scale = 0)
	public double getPayMoney() {
		return this.payMoney;
	}

	public void setPayMoney(double payMoney) {
		this.payMoney = payMoney;
	}

	@Column(name = "debt_money", nullable = false, precision = 22, scale = 0)
	public double getDebtMoney() {
		return this.debtMoney;
	}

	public void setDebtMoney(double debtMoney) {
		this.debtMoney = debtMoney;
	}

	@Column(name = "notice_status", nullable = false)
	public Integer getNoticeStatus() {
		return this.noticeStatus;
	}

	public void setNoticeStatus(Integer noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DebtNoticeId))
			return false;
		DebtNoticeId castOther = (DebtNoticeId) other;

		return (this.getBatchId() == castOther.getBatchId())
				&& (this.getPaymentId() == castOther.getPaymentId())
				&& (this.getBillMoney() == castOther.getBillMoney())
				&& (this.getPayMoney() == castOther.getPayMoney())
				&& (this.getDebtMoney() == castOther.getDebtMoney())
				&& ((this.getNoticeStatus() == castOther.getNoticeStatus()) || (this
						.getNoticeStatus() != null
						&& castOther.getNoticeStatus() != null && this
						.getNoticeStatus().equals(castOther.getNoticeStatus())));
	}
	

}