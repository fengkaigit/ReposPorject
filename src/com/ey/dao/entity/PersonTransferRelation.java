package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PersonTransferRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "person_transfer_relation")
public class PersonTransferRelation implements java.io.Serializable {

	// Fields

	private PersonTransferRelationId id;

	// Constructors

	/** default constructor */
	public PersonTransferRelation() {
	}

	/** full constructor */
	public PersonTransferRelation(PersonTransferRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "receiptsId", column = @Column(name = "receipts_id", nullable = false)),
			@AttributeOverride(name = "transferRecordsId", column = @Column(name = "transfer_records_id", nullable = false)) })
	public PersonTransferRelationId getId() {
		return this.id;
	}

	public void setId(PersonTransferRelationId id) {
		this.id = id;
	}

}