package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ProfitAccountRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "profit_account_relation")
public class ProfitTransferRelation implements java.io.Serializable {

	// Fields

	private ProfitTransferRelationId id;

	// Constructors

	/** default constructor */
	public ProfitTransferRelation() {
	}

	/** full constructor */
	public ProfitTransferRelation(ProfitTransferRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "receiptsId", column = @Column(name = "receipts_id", nullable = false)),
			@AttributeOverride(name = "transferRecordsId", column = @Column(name = "transfer_records_id", nullable = false)) })
	public ProfitTransferRelationId getId() {
		return this.id;
	}

	public void setId(ProfitTransferRelationId id) {
		this.id = id;
	}

}