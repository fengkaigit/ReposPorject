package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserAccountRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class UserAccountRelationId implements java.io.Serializable {

	// Fields

	private long id;
	private long cardNumber;

	// Constructors

	/** default constructor */
	public UserAccountRelationId() {
	}

	/** full constructor */
	public UserAccountRelationId(long id, long cardNumber) {
		this.id = id;
		this.cardNumber = cardNumber;
	}

	// Property accessors

	@Column(name = "ID", nullable = false)
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

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getId();
		result = 37 * result + (int) this.getCardNumber();
		return result;
	}

}