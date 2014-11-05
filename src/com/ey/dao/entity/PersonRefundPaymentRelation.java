package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PersonRefundPaymentRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "person_refund_payment_relation")
public class PersonRefundPaymentRelation implements java.io.Serializable {

	// Fields

	private PersonRefundPaymentRelationId id;

	// Constructors

	/** default constructor */
	public PersonRefundPaymentRelation() {
	}

	/** full constructor */
	public PersonRefundPaymentRelation(PersonRefundPaymentRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "paymentBillId", column = @Column(name = "payment_bill_id", nullable = false)),
			@AttributeOverride(name = "relationId", column = @Column(name = "relation_id", nullable = false)) })
	public PersonRefundPaymentRelationId getId() {
		return this.id;
	}

	public void setId(PersonRefundPaymentRelationId id) {
		this.id = id;
	}

}