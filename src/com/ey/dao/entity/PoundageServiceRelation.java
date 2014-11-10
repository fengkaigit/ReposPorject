package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * PoundageServiceRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "poundage_service_relation")
public class PoundageServiceRelation implements java.io.Serializable {

	// Fields

	private PoundageServiceRelationId id;

	// Constructors

	/** default constructor */
	public PoundageServiceRelation() {
	}

	/** full constructor */
	public PoundageServiceRelation(PoundageServiceRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "poundageId", column = @Column(name = "poundage_id", nullable = false)),
			@AttributeOverride(name = "serviceBillId", column = @Column(name = "service_bill_id", nullable = false)) })
	public PoundageServiceRelationId getId() {
		return this.id;
	}

	public void setId(PoundageServiceRelationId id) {
		this.id = id;
	}

}