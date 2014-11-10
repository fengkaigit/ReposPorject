package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * IncomeProfitRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class IncomeProfitRelationId implements java.io.Serializable {

	// Fields

	private Long profitId;
	private Long incomeId;

	// Constructors

	/** default constructor */
	public IncomeProfitRelationId() {
	}

	/** full constructor */
	public IncomeProfitRelationId(Long profitId, Long incomeId) {
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
		if (!(other instanceof IncomeProfitRelationId))
			return false;
		IncomeProfitRelationId castOther = (IncomeProfitRelationId) other;

		return (this.getProfitId() == castOther.getProfitId())
				&& (this.getIncomeId() == castOther.getIncomeId());
	}

}