package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * IncomeServiceRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "income_service_relation")
public class SteriliseHedgeRelation implements java.io.Serializable {

	// Fields

	private SteriliseHedgeRelationId id;

	// Constructors

	/** default constructor */
	public SteriliseHedgeRelation() {
	}

	/** full constructor */
	public SteriliseHedgeRelation(SteriliseHedgeRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "steriliseId", column = @Column(name = "sterilise_id", nullable = false)),
			@AttributeOverride(name = "hedgeId", column = @Column(name = "hedge_id", nullable = false)) })
	public SteriliseHedgeRelationId getId() {
		return this.id;
	}

	public void setId(SteriliseHedgeRelationId id) {
		this.id = id;
	}

}