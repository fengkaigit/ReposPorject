package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ProfitPaymentRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "profit_payment_relation")
public class ProfitPaymentRelation implements java.io.Serializable {

	// Fields

	private ProfitPaymentRelationId id;

	// Constructors

	/** default constructor */
	public ProfitPaymentRelation() {
	}

	/** full constructor */
	public ProfitPaymentRelation(ProfitPaymentRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "paymentBillId", column = @Column(name = "payment_bill_id", nullable = false)),
			@AttributeOverride(name = "relationId", column = @Column(name = "relation_id", nullable = false)) })
	public ProfitPaymentRelationId getId() {
		return this.id;
	}

	public void setId(ProfitPaymentRelationId id) {
		this.id = id;
	}

}