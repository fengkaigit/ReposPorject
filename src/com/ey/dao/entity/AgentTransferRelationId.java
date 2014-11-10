package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AgentTransferRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class AgentTransferRelationId implements java.io.Serializable {

	// Fields

	private Long id;
	private Long cardNumber;

	// Constructors

	/** default constructor */
	public AgentTransferRelationId() {
	}

	/** full constructor */
	public AgentTransferRelationId(Long id, Long cardNumber) {
		this.id = id;
		this.cardNumber = cardNumber;
	}

	// Property accessors

	@Column(name = "ID", nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "card_number", nullable = false)
	public Long getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
}