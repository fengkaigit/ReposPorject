package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AgentRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agent_rule")
public class AgentRule implements java.io.Serializable {

	// Fields

	private Long agentId;
	private String profile;
	private String IExplain;
	private String rule;
	private Date beginTime;
	private Date endTime;

	// Constructors

	/** default constructor */
	public AgentRule() {
	}

	/** minimal constructor */
	public AgentRule(Long agentId, Date beginTime) {
		this.agentId = agentId;
		this.beginTime = beginTime;
	}

	/** full constructor */
	public AgentRule(Long agentId, String profile, String IExplain,
			String rule, Date beginTime, Date endTime) {
		this.agentId = agentId;
		this.profile = profile;
		this.IExplain = IExplain;
		this.rule = rule;
		this.beginTime = beginTime;
		this.endTime = endTime;
	}

	// Property accessors
	@Id
	@Column(name = "agent_ID", unique = true, nullable = false)
	public Long getAgentId() {
		return this.agentId;
	}

	public void setAgentId(Long agentId) {
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

	@Column(name = "rule", length = 250)
	public String getRule() {
		return this.rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "begin_time", nullable = false, length = 10)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time", length = 10)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}