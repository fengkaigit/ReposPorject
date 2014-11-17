package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * EntRefundTransferRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ent_refund_transfer_relation")
public class EntRefundTransferRelation implements java.io.Serializable {

	// Fields

	private EntRefundTransferRelationId id;

	// Constructors

	/** default constructor */
	public EntRefundTransferRelation() {
	}

	/** full constructor */
	public EntRefundTransferRelation(EntRefundTransferRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "receiptsId", column = @Column(name = "receipts_id", nullable = false)),
			@AttributeOverride(name = "transferRecordsId", column = @Column(name = "transfer_records_id", nullable = false)) })
	public EntRefundTransferRelationId getId() {
		return this.id;
	}

	public void setId(EntRefundTransferRelationId id) {
		this.id = id;
	}

}