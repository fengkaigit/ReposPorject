package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * EntTransferPaymentRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ent_transfer_payment_relation")
public class EntTransferPaymentRelation implements java.io.Serializable {

	// Fields

	private EntTransferPaymentRelationId id;

	// Constructors

	/** default constructor */
	public EntTransferPaymentRelation() {
	}

	/** full constructor */
	public EntTransferPaymentRelation(EntTransferPaymentRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "paymentBillId", column = @Column(name = "payment_bill_id", nullable = false)),
			@AttributeOverride(name = "relationId", column = @Column(name = "relation_id", nullable = false)) })
	public EntTransferPaymentRelationId getId() {
		return this.id;
	}

	public void setId(EntTransferPaymentRelationId id) {
		this.id = id;
	}

}