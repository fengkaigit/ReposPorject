package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * AgentPaymentBatch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agent_payment_batch")
public class AgentPaymentBatch implements java.io.Serializable {

	// Fields

	private Long id;
	private double batchMoney;
	private Date createTime;
	private Date confirmTime;
	private Integer batchStatus;
	private Long agentId;
	private Long ruleId;
	private Integer payType;
	private Long billNum;
	private String payTypeName;

	// Constructors

	/** default constructor */
	public AgentPaymentBatch() {
	}

	/** minimal constructor */
	public AgentPaymentBatch(Long id, Date createTime, Integer batchStatus,
			Long agentId, Long ruleId) {
		this.id = id;
		this.createTime = createTime;
		this.batchStatus = batchStatus;
		this.agentId = agentId;
		this.ruleId = ruleId;
	}

	/** full constructor */
	public AgentPaymentBatch(Long id, double batchMoney, Date createTime,
			Date confirmTime, Integer batchStatus, Long agentId, Long ruleId) {
		this.id = id;
		this.batchMoney = batchMoney;
		this.createTime = createTime;
		this.confirmTime = confirmTime;
		this.batchStatus = batchStatus;
		this.agentId = agentId;
		this.ruleId = ruleId;
	}
	
	public AgentPaymentBatch(double batchMoney, Date createTime,
			Integer batchStatus, Long agentId,Integer payType,Long billNum,String payTypeName) {
		this.batchMoney = batchMoney;
		this.createTime = createTime;
		this.batchStatus = batchStatus;
		this.agentId = agentId;
		this.payType = payType;
		this.billNum = billNum;
		this.payTypeName = payTypeName;
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

	@Column(name = "batch_money", precision = 20, scale = 2)
	public double getBatchMoney() {
		return this.batchMoney;
	}

	public void setBatchMoney(double batchMoney) {
		this.batchMoney = batchMoney;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 10)
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
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
	public Long getAgentId() {
		return this.agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	@Column(name = "rule_id")
	public Long getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}
	@Column(name = "pay_type", nullable = false)
	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	@Column(name = "bill_num", nullable = false)
	public Long getBillNum() {
		return billNum;
	}

	public void setBillNum(Long billNum) {
		this.billNum = billNum;
	}
	@Column(name = "pay_typename")
	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	
	
	

}