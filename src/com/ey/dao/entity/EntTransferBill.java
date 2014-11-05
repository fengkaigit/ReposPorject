package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * EntTransferBill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ent_transfer_bill")
public class EntTransferBill implements java.io.Serializable {

	// Fields

	private long id;
	private Date createTime;
	private Date confirmTime;
	private double transferMoney;
	private Integer transferStatus;
	private Integer transferWay;
	private long entId;

	// Constructors

	/** default constructor */
	public EntTransferBill() {
	}

	/** minimal constructor */
	public EntTransferBill(long id, Date createTime, double transferMoney,
			Integer transferWay, long entId) {
		this.id = id;
		this.createTime = createTime;
		this.transferMoney = transferMoney;
		this.transferWay = transferWay;
		this.entId = entId;
	}

	/** full constructor */
	public EntTransferBill(long id, Date createTime, Date confirmTime,
			double transferMoney, Integer transferStatus, Integer transferWay,
			long entId) {
		this.id = id;
		this.createTime = createTime;
		this.confirmTime = confirmTime;
		this.transferMoney = transferMoney;
		this.transferStatus = transferStatus;
		this.transferWay = transferWay;
		this.entId = entId;
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

	@Column(name = "transfer_money", nullable = false, precision = 22, scale = 0)
	public double getTransferMoney() {
		return this.transferMoney;
	}

	public void setTransferMoney(double transferMoney) {
		this.transferMoney = transferMoney;
	}

	@Column(name = "transfer_status")
	public Integer getTransferStatus() {
		return this.transferStatus;
	}

	public void setTransferStatus(Integer transferStatus) {
		this.transferStatus = transferStatus;
	}

	@Column(name = "transfer_way", nullable = false)
	public Integer getTransferWay() {
		return this.transferWay;
	}

	public void setTransferWay(Integer transferWay) {
		this.transferWay = transferWay;
	}

	@Column(name = "ent_id", nullable = false)
	public long getEntId() {
		return this.entId;
	}

	public void setEntId(long entId) {
		this.entId = entId;
	}

}