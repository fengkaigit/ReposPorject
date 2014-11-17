package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TransferRateId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TransferRateId implements java.io.Serializable {

	// Fields

	private String bankId;
	private double limitMoney;
	private double rate;

	// Constructors

	/** default constructor */
	public TransferRateId() {
	}

	/** full constructor */
	public TransferRateId(String bankId, double limitMoney, double rate) {
		this.bankId = bankId;
		this.limitMoney = limitMoney;
		this.rate = rate;
	}

	// Property accessors

	@Column(name = "bank_id", nullable = false, length = 10)
	public String getBankId() {
		return this.bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	@Column(name = "limit_money", nullable = false, precision = 22, scale = 0)
	public double getLimitMoney() {
		return this.limitMoney;
	}

	public void setLimitMoney(double limitMoney) {
		this.limitMoney = limitMoney;
	}

	@Column(name = "rate", nullable = false, precision = 22, scale = 0)
	public double getRate() {
		return this.rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TransferRateId))
			return false;
		TransferRateId castOther = (TransferRateId) other;

		return ((this.getBankId() == castOther.getBankId()) || (this
				.getBankId() != null && castOther.getBankId() != null && this
				.getBankId().equals(castOther.getBankId())))
				&& (this.getLimitMoney() == castOther.getLimitMoney())
				&& (this.getRate() == castOther.getRate());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBankId() == null ? 0 : this.getBankId().hashCode());
		result = 37 * result + (int) this.getLimitMoney();
		result = 37 * result + (int) this.getRate();
		return result;
	}

}