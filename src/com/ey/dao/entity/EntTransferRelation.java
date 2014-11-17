package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * EntTransferRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ent_transfer_relation")
public class EntTransferRelation implements java.io.Serializable {

	// Fields

	private EntTransferRelationId id;

	// Constructors

	/** default constructor */
	public EntTransferRelation() {
	}

	/** full constructor */
	public EntTransferRelation(EntTransferRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "receiptsId", column = @Column(name = "receipts_id", nullable = false)),
			@AttributeOverride(name = "transferRecordsId", column = @Column(name = "transfer_records_id", nullable = false)) })
	public EntTransferRelationId getId() {
		return this.id;
	}

	public void setId(EntTransferRelationId id) {
		this.id = id;
	}

}