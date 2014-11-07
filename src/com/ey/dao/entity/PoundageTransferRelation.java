package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PoundageTransferRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "poundage_transfer_relation")
public class PoundageTransferRelation implements java.io.Serializable {

	// Fields

	private PoundageTransferRelationId id;

	// Constructors

	/** default constructor */
	public PoundageTransferRelation() {
	}

	/** full constructor */
	public PoundageTransferRelation(PoundageTransferRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "receiptsId", column = @Column(name = "receipts_id", nullable = false)),
			@AttributeOverride(name = "transferRecordsId", column = @Column(name = "transfer_records_id", nullable = false)) })
	public PoundageTransferRelationId getId() {
		return this.id;
	}

	public void setId(PoundageTransferRelationId id) {
		this.id = id;
	}

}