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

	private long id;
	private Date createTime;
	private Date confirmTime;
	private long transferMoney;
	private long status;
	private long entId;
	private Integer transferWay;

	// Constructors

	/** default constructor */
	public EntRefundBill() {
	}

	/** minimal constructor */
	public EntRefundBill(long id, Date createTime, long transferMoney,
			long status, long entId, Integer transferWay) {
		this.id = id;
		this.createTime = createTime;
		this.transferMoney = transferMoney;
		this.status = status;
		this.entId = entId;
		this.transferWay = transferWay;
	}

	/** full constructor */
	public EntRefundBill(long id, Date createTime, Date confirmTime,
			long transferMoney, long status, long entId, Integer transferWay) {
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

	@Column(name = "transfer_money", nullable = false)
	public long getTransferMoney() {
		return this.transferMoney;
	}

	public void setTransferMoney(long transferMoney) {
		this.transferMoney = transferMoney;
	}

	@Column(name = "status", nullable = false)
	public long getStatus() {
		return this.status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	@Column(name = "ent_id", nullable = false)
	public long getEntId() {
		return this.entId;
	}

	public void setEntId(long entId) {
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