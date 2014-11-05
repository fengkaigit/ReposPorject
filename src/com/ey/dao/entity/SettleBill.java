package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SettleBill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "settle_bill")
public class SettleBill implements java.io.Serializable {

	// Fields

	private long id;
	private Date createDate;
	private Date confirmDate;
	private long profitMoney;
	private Integer status;
	private long agentId;

	// Constructors

	/** default constructor */
	public SettleBill() {
	}

	/** minimal constructor */
	public SettleBill(long id, Date createDate, long profitMoney,
			Integer status, long agentId) {
		this.id = id;
		this.createDate = createDate;
		this.profitMoney = profitMoney;
		this.status = status;
		this.agentId = agentId;
	}

	/** full constructor */
	public SettleBill(long id, Date createDate, Date confirmDate,
			long profitMoney, Integer status, long agentId) {
		this.id = id;
		this.createDate = createDate;
		this.confirmDate = confirmDate;
		this.profitMoney = profitMoney;
		this.status = status;
		this.agentId = agentId;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "create_date", nullable = false, length = 10)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "confirm_date", length = 10)
	public Date getConfirmDate() {
		return this.confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	@Column(name = "profit_money", nullable = false)
	public long getProfitMoney() {
		return this.profitMoney;
	}

	public void setProfitMoney(long profitMoney) {
		this.profitMoney = profitMoney;
	}

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "agent_id", nullable = false)
	public long getAgentId() {
		return this.agentId;
	}

	public void setAgentId(long agentId) {
		this.agentId = agentId;
	}

}