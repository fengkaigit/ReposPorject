package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AgentRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agent_rule")
public class AgentRule implements java.io.Serializable {

	// Fields

	private long agentId;
	private String profile;
	private String IExplain;
	private String rule;

	// Constructors

	/** default constructor */
	public AgentRule() {
	}

	/** minimal constructor */
	public AgentRule(long agentId) {
		this.agentId = agentId;
	}

	/** full constructor */
	public AgentRule(long agentId, String profile, String IExplain, String rule) {
		this.agentId = agentId;
		this.profile = profile;
		this.IExplain = IExplain;
		this.rule = rule;
	}

	// Property accessors
	@Id
	@Column(name = "agent_ID", unique = true, nullable = false)
	public long getAgentId() {
		return this.agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	@Column(name = "profile", length = 250)
	public String getProfile() {
		return this.profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Column(name = "i_explain", length = 250)
	public String getIExplain() {
		return this.IExplain;
	}

	public void setIExplain(String IExplain) {
		this.IExplain = IExplain;
	}

	@Column(name = "rule", length = 120)
	public String getRule() {
		return this.rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

}