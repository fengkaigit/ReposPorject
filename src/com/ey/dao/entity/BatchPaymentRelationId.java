package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BatchPaymentRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class BatchPaymentRelationId implements java.io.Serializable {

	// Fields

	private Long paymentBillId;
	private Long relationId;

	// Constructors

	/** default constructor */
	public BatchPaymentRelationId() {
	}

	/** full constructor */
	public BatchPaymentRelationId(Long paymentBillId, Long relationId) {
		this.paymentBillId = paymentBillId;
		this.relationId = relationId;
	}

	// Property accessors

	@Column(name = "payment_bill_id", nullable = false)
	public Long getPaymentBillId() {
		return this.paymentBillId;
	}

	public void setPaymentBillId(Long paymentBillId) {
		this.paymentBillId = paymentBillId;
	}

	@Column(name = "relation_id", nullable = false)
	public Long getRelationId() {
		return this.relationId;
	}

	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BatchPaymentRelationId))
			return false;
		BatchPaymentRelationId castOther = (BatchPaymentRelationId) other;

		return (this.getPaymentBillId() == castOther.getPaymentBillId())
				&& (this.getRelationId() == castOther.getRelationId());
	}

}