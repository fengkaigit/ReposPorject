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
@Table(name = "sterilise_Bill")
public class SteriliseBill implements java.io.Serializable {

	// Fields

	private Long id;
	private Date createDate;
	private Date confirmDate;
	private Double steriliseMoney;
	private Integer status;

	// Constructors

	/** default constructor */
	public SteriliseBill() {
	}

	/** minimal constructor */
	public SteriliseBill(Long id, Date createDate, Double steriliseMoney,
			Integer status) {
		this.id = id;
		this.createDate = createDate;
		this.steriliseMoney = steriliseMoney;
		this.status = status;
	}

	/** full constructor */
	public SteriliseBill(Long id, Date createDate, Date confirmDate,
			Double steriliseMoney, Integer status) {
		this.id = id;
		this.createDate = createDate;
		this.confirmDate = confirmDate;
		this.steriliseMoney = steriliseMoney;
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

	@Column(name = "status", nullable = false)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "sterilise_Bill", precision = 20, scale = 2, nullable = false)
	public Double getSteriliseMoney() {
		return steriliseMoney;
	}

	public void setSteriliseMoney(Double steriliseMoney) {
		this.steriliseMoney = steriliseMoney;
	}

}