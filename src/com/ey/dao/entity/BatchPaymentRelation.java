package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BatchPaymentRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "batch_payment_relation")
public class BatchPaymentRelation implements java.io.Serializable {

	// Fields

	private BatchPaymentRelationId id;

	// Constructors

	/** default constructor */
	public BatchPaymentRelation() {
	}

	/** full constructor */
	public BatchPaymentRelation(BatchPaymentRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "paymentBillId", column = @Column(name = "payment_bill_id", nullable = false)),
			@AttributeOverride(name = "relationId", column = @Column(name = "relation_id", nullable = false)) })
	public BatchPaymentRelationId getId() {
		return this.id;
	}

	public void setId(BatchPaymentRelationId id) {
		this.id = id;
	}

}