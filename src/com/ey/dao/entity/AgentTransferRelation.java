package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AgentTransferRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agent_transfer_relation")
public class AgentTransferRelation implements java.io.Serializable {

	// Fields

	private AgentTransferRelationId id;

	// Constructors

	/** default constructor */
	public AgentTransferRelation() {
	}

	/** full constructor */
	public AgentTransferRelation(AgentTransferRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false)),
			@AttributeOverride(name = "cardNumber", column = @Column(name = "card_number", nullable = false)) })
	public AgentTransferRelationId getId() {
		return this.id;
	}

	public void setId(AgentTransferRelationId id) {
		this.id = id;
	}

}