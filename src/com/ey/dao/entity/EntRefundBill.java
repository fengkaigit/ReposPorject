package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * EntRefundBill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ent_refund_bill")
public class EntRefundBill implements java.io.Serializable {

	// Fields

	private Long id;
	private Date createTime;
	private Date confirmTime;
	private Double transferMoney;
	private Long status;
	private Long entId;
	private Integer transferWay;

	// Constructors

	/** default constructor */
	public EntRefundBill() {
	}

	/** minimal constructor */
	public EntRefundBill(Long id, Date createTime, Double transferMoney,
			Long status, Long entId, Integer transferWay) {
		this.id = id;
		this.createTime = createTime;
		this.transferMoney = transferMoney;
		this.status = status;
		this.entId = entId;
		this.transferWay = transferWay;
	}

	/** full constructor */
	public EntRefundBill(Long id, Date createTime, Date confirmTime,
			Double transferMoney, Long status, Long entId, Integer transferWay) {
		this.id = id;
		this.createTime = createTime;
		this.confirmTime = confirmTime;
		this.transferMoney = transferMoney;
		this.status = status;
		this.entId = entId;
		this.transferWay = transferWay;
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

	@Column(name = "transfer_money", precision = 20, scale = 2, nullable = false)
	public Double getTransferMoney() {
		return this.transferMoney;
	}

	public void setTransferMoney(Double transferMoney) {
		this.transferMoney = transferMoney;
	}

	@Column(name = "status", nullable = false)
	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "ent_id", nullable = false)
	public Long getEntId() {
		return this.entId;
	}

	public void setEntId(Long entId) {
		this.entId = entId;
	}

	@Column(name = "transfer_way", nullable = false)
	public Integer getTransferWay() {
		return this.transferWay;
	}

	public void setTransferWay(Integer transferWay) {
		this.transferWay = transferWay;
	}

}