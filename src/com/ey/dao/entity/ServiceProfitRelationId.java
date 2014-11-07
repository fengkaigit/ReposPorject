package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ServiceProfitRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ServiceProfitRelationId implements java.io.Serializable {

	// Fields

	private long profitId;
	private long incomeId;

	// Constructors

	/** default constructor */
	public ServiceProfitRelationId() {
	}

	/** full constructor */
	public ServiceProfitRelationId(long profitId, long incomeId) {
		this.profitId = profitId;
		this.incomeId = incomeId;
	}

	// Property accessors

	@Column(name = "profit_id", nullable = false)
	public long getProfitId() {
		return this.profitId;
	}

	public void setProfitId(long profitId) {
		this.profitId = profitId;
	}

	@Column(name = "income_id", nullable = false)
	public long getIncomeId() {
		return this.incomeId;
	}

	public void setIncomeId(long incomeId) {
		this.incomeId = incomeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ServiceProfitRelationId))
			return false;
		ServiceProfitRelationId castOther = (ServiceProfitRelationId) other;

		return (this.getProfitId() == castOther.getProfitId())
				&& (this.getIncomeId() == castOther.getIncomeId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getProfitId();
		result = 37 * result + (int) this.getIncomeId();
		return result;
	}

}