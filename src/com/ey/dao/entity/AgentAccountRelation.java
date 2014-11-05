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

	private long id;
	private long bankAccountId;

	// Constructors

	/** default constructor */
	public AgentAccountRelation() {
	}

	/** full constructor */
	public AgentAccountRelation(long id, long bankAccountId) {
		this.id = id;
		this.bankAccountId = bankAccountId;
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

	@Column(name = "bank_account_id", nullable = false)
	public long getBankAccountId() {
		return this.bankAccountId;
	}

	public void setBankAccountId(long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

}