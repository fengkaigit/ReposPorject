package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ProfitBill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "profit_bill")
public class ProfitBill implements java.io.Serializable {

	// Fields

	private long id;
	private Date createDate;
	private Date confirmDate;
	private long profitMoney;
	private Integer status;

	// Constructors

	/** default constructor */
	public ProfitBill() {
	}

	/** minimal constructor */
	public ProfitBill(long id, Date createDate, long profitMoney, Integer status) {
		this.id = id;
		this.createDate = createDate;
		this.profitMoney = profitMoney;
		this.status = status;
	}

	/** full constructor */
	public ProfitBill(long id, Date createDate, Date confirmDate,
			long profitMoney, Integer status) {
		this.id = id;
		this.createDate = createDate;
		this.confirmDate = confirmDate;
		this.profitMoney = profitMoney;
		this.status = status;
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

}