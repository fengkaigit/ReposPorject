package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * EntAccountRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ent_account_relation")
public class EntAccountRelation implements java.io.Serializable {

	// Fields

	private EntAccountRelationId id;
	private Boolean flag;

	// Constructors

	/** default constructor */
	public EntAccountRelation() {
	}

	/** full constructor */
	public EntAccountRelation(EntAccountRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false)),
			@AttributeOverride(name = "cardNumber", column = @Column(name = "card_number", nullable = false)) })
	public EntAccountRelationId getId() {
		return this.id;
	}

	public void setId(EntAccountRelationId id) {
		this.id = id;
	}
	@Column(name = "flag")
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

}