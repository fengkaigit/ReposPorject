package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EntAccountRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class EntAccountRelationId implements java.io.Serializable {

	// Fields

	private Long id;
	private Long cardNumber;

	// Constructors

	/** default constructor */
	public EntAccountRelationId() {
	}

	/** full constructor */
	public EntAccountRelationId(Long id, Long cardNumber) {
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EntAccountRelationId))
			return false;
		EntAccountRelationId castOther = (EntAccountRelationId) other;

		return (this.getId() == castOther.getId())
				&& (this.getCardNumber() == castOther.getCardNumber());
	}
}