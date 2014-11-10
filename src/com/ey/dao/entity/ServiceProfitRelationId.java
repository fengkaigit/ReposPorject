package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ServiceProfitRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ServiceProfitRelationId implements java.io.Serializable {

	// Fields

	private Long profitId;
	private Long incomeId;

	// Constructors

	/** default constructor */
	public ServiceProfitRelationId() {
	}

	/** full constructor */
	public ServiceProfitRelationId(Long profitId, Long incomeId) {
		this.profitId = profitId;
		this.incomeId = incomeId;
	}

	// Property accessors

	@Column(name = "profit_id", nullable = false)
	public Long getProfitId() {
		return this.profitId;
	}

	public void setProfitId(Long profitId) {
		this.profitId = profitId;
	}

	@Column(name = "income_id", nullable = false)
	public Long getIncomeId() {
		return this.incomeId;
	}

	public void setIncomeId(Long incomeId) {
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
}