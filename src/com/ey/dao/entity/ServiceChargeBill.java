package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ServiceChargeBill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "service_charge_bill")
public class ServiceChargeBill implements java.io.Serializable {

	// Fields

	private Long id;
	private Date createDate;
	private Date confirmDate;
	private double profitMoney;
	private Integer status;

	// Constructors

	/** default constructor */
	public ServiceChargeBill() {
	}

	/** minimal constructor */
	public ServiceChargeBill(Long id, Date createDate, double profitMoney,
			Integer status) {
		this.id = id;
		this.createDate = createDate;
		this.profitMoney = profitMoney;
		this.status = status;
	}

	/** full constructor */
	public ServiceChargeBill(Long id, Date createDate, Date confirmDate,
			double profitMoney, Integer status) {
		this.id = id;
		this.createDate = createDate;
		this.confirmDate = confirmDate;
		this.profitMoney = profitMoney;
		this.status = status;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = false, length = 10)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "confirm_date", length = 10)
	public Date getConfirmDate() {
		return this.confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	@Column(name = "profit_money", nullable = false, precision = 22, scale = 0)
	public double getProfitMoney() {
		return this.profitMoney;
	}

	public void setProfitMoney(double profitMoney) {
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