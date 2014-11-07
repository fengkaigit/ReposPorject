package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ServiceTransferRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "service_transfer_relation")
public class ServiceTransferRelation implements java.io.Serializable {

	// Fields

	private ServiceTransferRelationId id;

	// Constructors

	/** default constructor */
	public ServiceTransferRelation() {
	}

	/** full constructor */
	public ServiceTransferRelation(ServiceTransferRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "receiptsId", column = @Column(name = "receipts_id", nullable = false)),
			@AttributeOverride(name = "transferRecordsId", column = @Column(name = "transfer_records_id", nullable = false)) })
	public ServiceTransferRelationId getId() {
		return this.id;
	}

	public void setId(ServiceTransferRelationId id) {
		this.id = id;
	}

}