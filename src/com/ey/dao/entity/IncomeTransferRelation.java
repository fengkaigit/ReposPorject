package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * IncomeTransferRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "income_transfer_relation")
public class IncomeTransferRelation implements java.io.Serializable {

	// Fields

	private IncomeTransferRelationId id;

	// Constructors

	/** default constructor */
	public IncomeTransferRelation() {
	}

	/** full constructor */
	public IncomeTransferRelation(IncomeTransferRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "receiptsId", column = @Column(name = "receipts_id", nullable = false)),
			@AttributeOverride(name = "transferRecordsId", column = @Column(name = "transfer_records_id", nullable = false)) })
	public IncomeTransferRelationId getId() {
		return this.id;
	}

	public void setId(IncomeTransferRelationId id) {
		this.id = id;
	}

}