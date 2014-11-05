package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EntAccountRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ent_account_relation")
public class EntAccountRelation implements java.io.Serializable {

	// Fields

	private long id;
	private long cardNumber;

	// Constructors

	/** default constructor */
	public EntAccountRelation() {
	}

	/** full constructor */
	public EntAccountRelation(long id, long cardNumber) {
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