package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SettleTransferRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "settle_transfer_relation")
public class SettleTransferRelation implements java.io.Serializable {

	// Fields

	private SettleTransferRelationId id;

	// Constructors

	/** default constructor */
	public SettleTransferRelation() {
	}

	/** full constructor */
	public SettleTransferRelation(SettleTransferRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "receiptsId", column = @Column(name = "receipts_id", nullable = false)),
			@AttributeOverride(name = "transferRecordsId", column = @Column(name = "transfer_records_id", nullable = false)) })
	public SettleTransferRelationId getId() {
		return this.id;
	}

	public void setId(SettleTransferRelationId id) {
		this.id = id;
	}

}