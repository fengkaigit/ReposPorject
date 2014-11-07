package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PoundageServiceRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class PoundageServiceRelationId implements java.io.Serializable {

	// Fields

	private long poundageId;
	private long serviceId;

	// Constructors

	/** default constructor */
	public PoundageServiceRelationId() {
	}

	/** full constructor */
	public PoundageServiceRelationId(long poundageId, long serviceId) {
		this.poundageId = poundageId;
		this.serviceId = serviceId;
	}

	// Property accessors

	@Column(name = "poundage_id", nullable = false)
	public long getPoundageId() {
		return this.poundageId;
	}

	public void setPoundageId(long poundageId) {
		this.poundageId = poundageId;
	}

	@Column(name = "service_id", nullable = false)
	public long getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
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
				&& (this.getServiceId() == castOther.getServiceId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getPoundageId();
		result = 37 * result + (int) this.getServiceId();
		return result;
	}

}