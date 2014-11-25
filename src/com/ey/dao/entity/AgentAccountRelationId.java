package com.ey.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class AgentAccountRelationId implements Serializable {
	private Long id;
	private Long bankAccountId;
	
	
	public AgentAccountRelationId() {
	}

	/** full constructor */
	public AgentAccountRelationId(Long id, Long bankAccountId) {
		this.id = id;
		this.bankAccountId = bankAccountId;
	}

	// Property accessors
	@Column(name = "ID", nullable = false)
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
