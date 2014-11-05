package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SettleProfitRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SettleProfitRelationId implements java.io.Serializable {

	// Fields

	private long profitId;
	private long settleId;

	// Constructors

	/** default constructor */
	public SettleProfitRelationId() {
	}

	/** full constructor */
	public SettleProfitRelationId(long profitId, long settleId) {
		this.profitId = profitId;
		this.settleId = settleId;
	}

	// Property accessors

	@Column(name = "profit_id", nullable = false)
	public long getProfitId() {
		return this.profitId;
	}

	public void setProfitId(long profitId) {
		this.profitId = profitId;
	}

	@Column(name = "settle_id", nullable = false)
	public long getSettleId() {
		return this.settleId;
	}

	public void setSettleId(long settleId) {
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

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getProfitId();
		result = 37 * result + (int) this.getSettleId();
		return result;
	}

}