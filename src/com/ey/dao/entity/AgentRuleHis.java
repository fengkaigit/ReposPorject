package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AgentRuleHis entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agent_rule_his")
public class AgentRuleHis implements java.io.Serializable {

	// Fields

	private long id;
	private long agentId;
	private String profile;
	private String IExplain;
	private String rule;

	// Constructors

	/** default constructor */
	public AgentRuleHis() {
	}

	/** minimal constructor */
	public AgentRuleHis(long id, long agentId) {
		this.id = id;
		this.agentId = agentId;
	}

	/** full constructor */
	public AgentRuleHis(long id, long agentId, String profile, String IExplain,
			String rule) {
		this.id = id;
		this.agentId = agentId;
		this.profile = profile;
		this.IExplain = IExplain;
		this.rule = rule;
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

	@Column(name = "agent_ID", nullable = false)
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