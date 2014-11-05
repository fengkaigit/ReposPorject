package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * EntRefundPaymentRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ent_refund_payment_relation")
public class EntRefundPaymentRelation implements java.io.Serializable {

	// Fields

	private EntRefundPaymentRelationId id;

	// Constructors

	/** default constructor */
	public EntRefundPaymentRelation() {
	}

	/** full constructor */
	public EntRefundPaymentRelation(EntRefundPaymentRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "paymentBillId", column = @Column(name = "payment_bill_id", nullable = false)),
			@AttributeOverride(name = "relationId", column = @Column(name = "relation_id", nullable = false)) })
	public EntRefundPaymentRelationId getId() {
		return this.id;
	}

	public void setId(EntRefundPaymentRelationId id) {
		this.id = id;
	}

}