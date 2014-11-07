package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DebtNoticeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class DebtNoticeId implements java.io.Serializable {

	// Fields

	private long batchId;
	private long paymentId;
	private double billMoney;
	private double payMoney;
	private double debtMoney;
	private Integer noticeStatus;

	// Constructors

	/** default constructor */
	public DebtNoticeId() {
	}

	/** full constructor */
	public DebtNoticeId(long batchId, long paymentId, double billMoney,
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
	public long getBatchId() {
		return this.batchId;
	}

	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}

	@Column(name = "payment_id", nullable = false)
	public long getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(long paymentId) {
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

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getBatchId();
		result = 37 * result + (int) this.getPaymentId();
		result = 37 * result + (int) this.getBillMoney();
		result = 37 * result + (int) this.getPayMoney();
		result = 37 * result + (int) this.getDebtMoney();
		result = 37
				* result
				+ (getNoticeStatus() == null ? 0 : this.getNoticeStatus()
						.hashCode());
		return result;
	}

}