package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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

	
	private AgentAccountRelationId id;
	private Boolean flag;

	// Constructors


	/** default constructor */
	public AgentAccountRelation() {
	}

	/** full constructor */
	public AgentAccountRelation(AgentAccountRelationId id,Boolean flag) {
		this.id = id;
		this.flag = flag;
	}
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false)),
			@AttributeOverride(name = "bankAccountId", column = @Column(name = "bank_account_id", nullable = false)) })
	public AgentAccountRelationId getId() {
		return this.id;
	}

	public void setId(AgentAccountRelationId id) {
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