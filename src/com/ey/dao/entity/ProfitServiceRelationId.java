package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProfitServiceRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ProfitServiceRelationId implements java.io.Serializable {

	// Fields

	private Long paymentBillId;
	private Long profitBillId;

	// Constructors

	/** default constructor */
	public ProfitServiceRelationId() {
	}

	/** full constructor */
	public ProfitServiceRelationId(Long paymentBillId, Long profitBillId) {
		this.paymentBillId = paymentBillId;
		this.profitBillId = profitBillId;
	}

	// Property accessors

	@Column(name = "payment_bill_id", nullable = false)
	public Long getPaymentBillId() {
		return this.paymentBillId;
	}

	public void setPaymentBillId(Long paymentBillId) {
		this.paymentBillId = paymentBillId;
	}

	@Column(name = "profit_bill_id", nullable = false)
	public Long getProfitBillId() {
		return this.profitBillId;
	}

	public void setRelationId(Long profitBillId) {
		this.profitBillId = profitBillId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProfitServiceRelationId))
			return false;
		ProfitServiceRelationId castOther = (ProfitServiceRelationId) other;

		return (this.getPaymentBillId() == castOther.getPaymentBillId())
				&& (this.getProfitBillId() == castOther.getProfitBillId());
	}

}