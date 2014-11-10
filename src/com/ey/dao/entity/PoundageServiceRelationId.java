package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PoundageServiceRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class PoundageServiceRelationId implements java.io.Serializable {

	// Fields

	private Long poundageId;
	private Long serviceBillId;

	// Constructors

	/** default constructor */
	public PoundageServiceRelationId() {
	}

	/** full constructor */
	public PoundageServiceRelationId(Long poundageId, Long serviceBillId) {
		this.poundageId = poundageId;
		this.serviceBillId = serviceBillId;
	}

	// Property accessors

	@Column(name = "poundage_id", nullable = false)
	public Long getPoundageId() {
		return this.poundageId;
	}

	public void setPoundageId(Long poundageId) {
		this.poundageId = poundageId;
	}

	@Column(name = "service_bill_id", nullable = false)
	public Long getServiceBillId() {
		return this.serviceBillId;
	}

	public void setServiceBillId(Long serviceBillId) {
		this.serviceBillId = serviceBillId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PoundageServiceRelationId))
			return false;
		PoundageServiceRelationId castOther = (PoundageServiceRelationId) other;

		return (this.getPoundageId() == castOther.getPoundageId())
				&& (this.getServiceBillId() == castOther.getServiceBillId());
	}

}