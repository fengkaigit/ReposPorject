package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AgentAccountRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agent_account_relation")
public class AgentAccountRelation implements java.io.Serializable {

	// Fields

	private Long id;
	private Long bankAccountId;

	// Constructors

	/** default constructor */
	public AgentAccountRelation() {
	}

	/** full constructor */
	public AgentAccountRelation(Long id, Long bankAccountId) {
		this.id = id;
		this.bankAccountId = bankAccountId;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "bank_account_id", nullable = false)
	public Long getBankAccountId() {
		return this.bankAccountId;
	}

	public void setBankAccountId(Long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

}