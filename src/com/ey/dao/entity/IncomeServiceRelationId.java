package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * IncomeServiceRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class IncomeServiceRelationId implements java.io.Serializable {

	// Fields

	private Long serviceBillId;
	private Long incomeBillId;

	// Constructors

	/** default constructor */
	public IncomeServiceRelationId() {
	}

	/** full constructor */
	public IncomeServiceRelationId(Long serviceBillId, Long incomeBillId) {
		this.serviceBillId = serviceBillId;
		this.incomeBillId = incomeBillId;
	}

	// Property accessors

	@Column(name = "service_bill_id", nullable = false)
	public Long getServiceBillId() {
		return this.serviceBillId;
	}

	public void setServiceBillId(Long serviceBillId) {
		this.serviceBillId = serviceBillId;
	}

	@Column(name = "income_bill_id", nullable = false)
	public Long getIncomeBillId() {
		return this.incomeBillId;
	}

	public void setIncomeBillId(Long incomeBillId) {
		this.incomeBillId = incomeBillId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IncomeServiceRelationId))
			return false;
		IncomeServiceRelationId castOther = (IncomeServiceRelationId) other;

		return (this.getServiceBillId() == castOther.getServiceBillId())
				&& (this.getIncomeBillId() == castOther.getIncomeBillId());
	}

}