package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AgentBatchRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agent_batch_rule")
public class AgentBatchRule implements java.io.Serializable {

	// Fields

	private long id;
	private String profile;
	private String IExplain;
	private String rule;
	private long agentId;
	private Integer ruleStatus;

	// Constructors

	/** default constructor */
	public AgentBatchRule() {
	}

	/** minimal constructor */
	public AgentBatchRule(long id, String rule, long agentId, Integer ruleStatus) {
		this.id = id;
		this.rule = rule;
		this.agentId = agentId;
		this.ruleStatus = ruleStatus;
	}

	/** full constructor */
	public AgentBatchRule(long id, String profile, String IExplain,
			String rule, long agentId, Integer ruleStatus) {
		this.id = id;
		this.profile = profile;
		this.IExplain = IExplain;
		this.rule = rule;
		this.agentId = agentId;
		this.ruleStatus = ruleStatus;
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

	@Column(name = "rule", nullable = false, length = 120)
	public String getRule() {
		return this.rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	@Column(name = "agent_id", nullable = false)
	public long getAgentId() {
		return this.agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	@Column(name = "rule_status", nullable = false)
	public Integer getRuleStatus() {
		return this.ruleStatus;
	}

	public void setRuleStatus(Integer ruleStatus) {
		this.ruleStatus = ruleStatus;
	}

}