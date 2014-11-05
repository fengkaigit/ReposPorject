package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SettleProfitRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "settle_profit_relation")
public class SettleProfitRelation implements java.io.Serializable {

	// Fields

	private SettleProfitRelationId id;

	// Constructors

	/** default constructor */
	public SettleProfitRelation() {
	}

	/** full constructor */
	public SettleProfitRelation(SettleProfitRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "profitId", column = @Column(name = "profit_id", nullable = false)),
			@AttributeOverride(name = "settleId", column = @Column(name = "settle_id", nullable = false)) })
	public SettleProfitRelationId getId() {
		return this.id;
	}

	public void setId(SettleProfitRelationId id) {
		this.id = id;
	}

}