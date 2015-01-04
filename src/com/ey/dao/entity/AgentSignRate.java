package com.ey.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "agent_sign_rate")
public class AgentSignRate implements Serializable {
    private Long id;
    private Long agentId;
    private Date beginTime;
    private Date endTime;
    private Double signRate;
    
    
    public AgentSignRate() {
		super();
	}
	public AgentSignRate(Long id, Long agentId, Date beginTime, Date endTime,
			Double signRate) {
		super();
		this.id = id;
		this.agentId = agentId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.signRate = signRate;
	}
    public AgentSignRate(Long agentId, Date beginTime, Date endTime,
			Double signRate) {
		super();
		this.id = id;
		this.agentId = agentId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.signRate = signRate;
	}
    public AgentSignRate(Long id, Long agentId, Date beginTime, Date endTime) {
		super();
		this.id = id;
		this.agentId = agentId;
		this.beginTime = beginTime;
		this.endTime = endTime;
	}
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "agent_id", nullable = false)
	public Long getAgentId() {
		return agentId;
	}
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "begin_time")
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "end_time")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name = "sign_rate")
	public Double getSignRate() {
		return signRate;
	}
	public void setSignRate(Double signRate) {
		this.signRate = signRate;
	}
    
    
    
}
