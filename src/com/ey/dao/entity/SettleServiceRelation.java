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
@Table(name = "settle_service_relation")
public class SettleServiceRelation implements java.io.Serializable {

	// Fields

	private SettleServiceRelationId id;

	// Constructors

	/** default constructor */
	public SettleServiceRelation() {
	}

	/** full constructor */
	public SettleServiceRelation(SettleServiceRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "serviceBillId", column = @Column(name = "service_bill_id", nullable = false)),
			@AttributeOverride(name = "settleBillId", column = @Column(name = "settle_bill_id", nullable = false)) })
	public SettleServiceRelationId getId() {
		return this.id;
	}

	public void setId(SettleServiceRelationId id) {
		this.id = id;
	}

}