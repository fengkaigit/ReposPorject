package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BatchPaymentRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class BatchPaymentRelationId implements java.io.Serializable {

	// Fields

	private long paymentBillId;
	private long relationId;

	// Constructors

	/** default constructor */
	public BatchPaymentRelationId() {
	}

	/** full constructor */
	public BatchPaymentRelationId(long paymentBillId, long relationId) {
		this.paymentBillId = paymentBillId;
		this.relationId = relationId;
	}

	// Property accessors

	@Column(name = "payment_bill_id", nullable = false)
	public long getPaymentBillId() {
		return this.paymentBillId;
	}

	public void setPaymentBillId(long paymentBillId) {
		this.paymentBillId = paymentBillId;
	}

	@Column(name = "relation_id", nullable = false)
	public long getRelationId() {
		return this.relationId;
	}

	public void setRelationId(long relationId) {
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

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getPaymentBillId();
		result = 37 * result + (int) this.getRelationId();
		return result;
	}

}