package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProfitServiceRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ProfitServiceRelationId implements java.io.Serializable {

	// Fields

	private Long serviceBillId;
	private Long profitBillId;

	// Constructors

	/** default constructor */
	public ProfitServiceRelationId() {
	}

	/** full constructor */
	public ProfitServiceRelationId(Long serviceBillId, Long profitBillId) {
		this.serviceBillId = serviceBillId;
		this.profitBillId = profitBillId;
	}

	// Property accessors

	@Column(name = "service_bill_id", nullable = false)
	public Long getServiceBillId() {
		return this.serviceBillId;
	}

	public void setServiceBillId(Long serviceBillId) {
		this.serviceBillId = serviceBillId;
	}

	@Column(name = "profit_bill_id", nullable = false)
	public Long getProfitBillId() {
		return this.profitBillId;
	}

	public void setProfitBillId(Long profitBillId) {
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

		return (this.getServiceBillId() == castOther.getServiceBillId())
				&& (this.getProfitBillId() == castOther.getProfitBillId());
	}


}