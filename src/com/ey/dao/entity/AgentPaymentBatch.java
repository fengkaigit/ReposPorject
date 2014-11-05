package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AgentPaymentBatch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agent_payment_batch")
public class AgentPaymentBatch implements java.io.Serializable {

	// Fields

	private long id;
	private double batchMoney;
	private Date createTime;
	private Date confirmTime;
	private Integer batchStatus;
	private long agentId;
	private long ruleId;

	// Constructors

	/** default constructor */
	public AgentPaymentBatch() {
	}

	/** minimal constructor */
	public AgentPaymentBatch(long id, Date createTime, Integer batchStatus,
			long agentId, long ruleId) {
		this.id = id;
		this.createTime = createTime;
		this.batchStatus = batchStatus;
		this.agentId = agentId;
		this.ruleId = ruleId;
	}

	/** full constructor */
	public AgentPaymentBatch(long id, double batchMoney, Date createTime,
			Date confirmTime, Integer batchStatus, long agentId, long ruleId) {
		this.id = id;
		this.batchMoney = batchMoney;
		this.createTime = createTime;
		this.confirmTime = confirmTime;
		this.batchStatus = batchStatus;
		this.agentId = agentId;
		this.ruleId = ruleId;
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

	@Column(name = "batch_money", precision = 22, scale = 0)
	public double getBatchMoney() {
		return this.batchMoney;
	}

	public void setBatchMoney(double batchMoney) {
		this.batchMoney = batchMoney;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "create_time", nullable = false, length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "confirm_time", length = 10)
	public Date getConfirmTime() {
		return this.confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	@Column(name = "batch_status", nullable = false)
	public Integer getBatchStatus() {
		return this.batchStatus;
	}

	public void setBatchStatus(Integer batchStatus) {
		this.batchStatus = batchStatus;
	}

	@Column(name = "agent_id", nullable = false)
	public long getAgentId() {
		return this.agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

	@Column(name = "rule_id", nullable = false)
	public long getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(long ruleId) {
		this.ruleId = ruleId;
	}

}