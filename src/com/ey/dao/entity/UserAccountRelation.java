package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UserAccountRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_account_relation")
public class UserAccountRelation implements java.io.Serializable {

	// Fields

	private UserAccountRelationId id;

	// Constructors

	/** default constructor */
	public UserAccountRelation() {
	}

	/** full constructor */
	public UserAccountRelation(UserAccountRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false)),
			@AttributeOverride(name = "cardNumber", column = @Column(name = "card_number", nullable = false)) })
	public UserAccountRelationId getId() {
		return this.id;
	}

	public void setId(UserAccountRelationId id) {
		this.id = id;
	}

}