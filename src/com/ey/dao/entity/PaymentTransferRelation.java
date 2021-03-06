package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PaymentTransferRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "payment_transfer_relation")
public class PaymentTransferRelation implements java.io.Serializable {

	// Fields

	private PaymentTransferRelationId id;

	// Constructors

	/** default constructor */
	public PaymentTransferRelation() {
	}

	/** full constructor */
	public PaymentTransferRelation(PaymentTransferRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "receiptsId", column = @Column(name = "receipts_id", nullable = false)),
			@AttributeOverride(name = "transferRecordsId", column = @Column(name = "transfer_records_id", nullable = false)) })
	public PaymentTransferRelationId getId() {
		return this.id;
	}

	public void setId(PaymentTransferRelationId id) {
		this.id = id;
	}

}