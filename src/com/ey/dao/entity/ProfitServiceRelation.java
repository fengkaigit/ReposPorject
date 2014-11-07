package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ProfitServiceRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "profit_service_relation")
public class ProfitServiceRelation implements java.io.Serializable {

	// Fields

	private ProfitServiceRelationId id;

	// Constructors

	/** default constructor */
	public ProfitServiceRelation() {
	}

	/** full constructor */
	public ProfitServiceRelation(ProfitServiceRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "paymentBillId", column = @Column(name = "payment_bill_id", nullable = false)),
			@AttributeOverride(name = "relationId", column = @Column(name = "relation_id", nullable = false)) })
	public ProfitServiceRelationId getId() {
		return this.id;
	}

	public void setId(ProfitServiceRelationId id) {
		this.id = id;
	}

}