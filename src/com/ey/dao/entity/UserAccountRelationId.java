package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserAccountRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class UserAccountRelationId implements java.io.Serializable {

	// Fields

	private Long id;
	private Long cardNumber;

	// Constructors

	/** default constructor */
	public UserAccountRelationId() {
	}

	/** full constructor */
	public UserAccountRelationId(Long id, Long cardNumber) {
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
		if (!(other instanceof UserAccountRelationId))
			return false;
		UserAccountRelationId castOther = (UserAccountRelationId) other;

		return (this.getId() == castOther.getId())
				&& (this.getCardNumber() == castOther.getCardNumber());
	}
}