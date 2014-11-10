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
@Table(name = "income_service_relation")
public class IncomeServiceRelation implements java.io.Serializable {

	// Fields

	private IncomeServiceRelationId id;

	// Constructors

	/** default constructor */
	public IncomeServiceRelation() {
	}

	/** full constructor */
	public IncomeServiceRelation(IncomeServiceRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "serviceBillId", column = @Column(name = "service_bill_id", nullable = false)),
			@AttributeOverride(name = "incomeBillId", column = @Column(name = "income_bill_id", nullable = false)) })
	public IncomeServiceRelationId getId() {
		return this.id;
	}

	public void setId(IncomeServiceRelationId id) {
		this.id = id;
	}

}