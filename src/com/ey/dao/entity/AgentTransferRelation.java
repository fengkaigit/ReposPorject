package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AgentTransferRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agent_transfer_relation")
public class AgentTransferRelation implements java.io.Serializable {

	// Fields

	private long id;
	private long cardNumber;

	// Constructors

	/** default constructor */
	public AgentTransferRelation() {
	}

	/** full constructor */
	public AgentTransferRelation(long id, long cardNumber) {
		this.id = id;
		this.cardNumber = cardNumber;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "card_number", nullable = false)
	public long getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

}