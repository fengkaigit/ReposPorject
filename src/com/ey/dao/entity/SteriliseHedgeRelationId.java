package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * IncomeServiceRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SteriliseHedgeRelationId implements java.io.Serializable {

	// Fields

	private Long hedgeId;
	private Long steriliseId;

	// Constructors

	/** default constructor */
	public SteriliseHedgeRelationId() {
	}

	/** full constructor */
	public SteriliseHedgeRelationId(Long steriliseId, Long hedgeId) {
		this.steriliseId = steriliseId;
		this.hedgeId = hedgeId;
	}

	// Property accessors

	@Column(name = "sterilise_id", nullable = false)
	public Long getSteriliseId() {
		return this.steriliseId;
	}

	public void setSteriliseId(Long steriliseId) {
		this.steriliseId = steriliseId;
	}

	@Column(name = "hedge_id", nullable = false)
	public Long getHedgeId() {
		return this.hedgeId;
	}

	public void setHedgeId(Long hedgeId) {
		this.hedgeId = hedgeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SteriliseHedgeRelationId))
			return false;
		SteriliseHedgeRelationId castOther = (SteriliseHedgeRelationId) other;

		return (this.getSteriliseId() == castOther.getSteriliseId())
				&& (this.getHedgeId() == castOther.getHedgeId());
	}

}