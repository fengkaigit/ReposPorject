package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * IncomeProfitRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "income_profit_relation")
public class IncomeProfitRelation implements java.io.Serializable {

	// Fields

	private IncomeProfitRelationId id;

	// Constructors

	/** default constructor */
	public IncomeProfitRelation() {
	}

	/** full constructor */
	public IncomeProfitRelation(IncomeProfitRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "profitId", column = @Column(name = "profit_id", nullable = false)),
			@AttributeOverride(name = "incomeId", column = @Column(name = "income_id", nullable = false)) })
	public IncomeProfitRelationId getId() {
		return this.id;
	}

	public void setId(IncomeProfitRelationId id) {
		this.id = id;
	}

}