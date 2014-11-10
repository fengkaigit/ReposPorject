package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SettleProfitRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SettleProfitRelationId implements java.io.Serializable {

	// Fields

	private Long profitId;
	private Long settleId;

	// Constructors

	/** default constructor */
	public SettleProfitRelationId() {
	}

	/** full constructor */
	public SettleProfitRelationId(Long profitId, Long settleId) {
		this.profitId = profitId;
		this.settleId = settleId;
	}

	// Property accessors

	@Column(name = "profit_id", nullable = false)
	public Long getProfitId() {
		return this.profitId;
	}

	public void setProfitId(Long profitId) {
		this.profitId = profitId;
	}

	@Column(name = "settle_id", nullable = false)
	public Long getSettleId() {
		return this.settleId;
	}

	public void setSettleId(Long settleId) {
		this.settleId = settleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SettleProfitRelationId))
			return false;
		SettleProfitRelationId castOther = (SettleProfitRelationId) other;

		return (this.getProfitId() == castOther.getProfitId())
				&& (this.getSettleId() == castOther.getSettleId());
	}
}