package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TransferRecords entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "transfer_records")
public class TransferRecords implements java.io.Serializable {

	// Fields

	private Long id;
	private Date transferTime;
	private double poundage;
	private double transferMoney;
	private Long transferOutAccountId;
	private Long transferInAccountId;
	private Integer transferType;
	private Integer transferStatus;

	// Constructors

	/** default constructor */
	public TransferRecords() {
	}

	/** minimal constructor */
	public TransferRecords(Long id, Date transferTime, double poundage,
			double transferMoney, Long transferOutAccountId,
			Long transferInAccountId, Integer transferType) {
		this.id = id;
		this.transferTime = transferTime;
		this.poundage = poundage;
		this.transferMoney = transferMoney;
		this.transferOutAccountId = transferOutAccountId;
		this.transferInAccountId = transferInAccountId;
		this.transferType = transferType;
	}

	/** full constructor */
	public TransferRecords(Long id, Date transferTime, double poundage,
			double transferMoney, Long transferOutAccountId,
			Long transferInAccountId, Integer transferType,
			Integer transferStatus) {
		this.id = id;
		this.transferTime = transferTime;
		this.poundage = poundage;
		this.transferMoney = transferMoney;
		this.transferOutAccountId = transferOutAccountId;
		this.transferInAccountId = transferInAccountId;
		this.transferType = transferType;
		this.transferStatus = transferStatus;
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
	@Column(name = "transfer_time", nullable = false, length = 10)
	public Date getTransferTime() {
		return this.transferTime;
	}

	public void setTransferTime(Date transferTime) {
		this.transferTime = transferTime;
	}

	@Column(name = "poundage", nullable = false, precision = 22, scale = 0)
	public double getPoundage() {
		return this.poundage;
	}

	public void setPoundage(double poundage) {
		this.poundage = poundage;
	}

	@Column(name = "transfer_money", nullable = false, precision = 22, scale = 0)
	public double getTransferMoney() {
		return this.transferMoney;
	}

	public void setTransferMoney(double transferMoney) {
		this.transferMoney = transferMoney;
	}

	@Column(name = "transfer_out_account_id", nullable = false)
	public Long getTransferOutAccountId() {
		return this.transferOutAccountId;
	}

	public void setTransferOutAccountId(Long transferOutAccountId) {
		this.transferOutAccountId = transferOutAccountId;
	}

	@Column(name = "transfer_in_account_id", nullable = false)
	public Long getTransferInAccountId() {
		return this.transferInAccountId;
	}

	public void setTransferInAccountId(Long transferInAccountId) {
		this.transferInAccountId = transferInAccountId;
	}

	@Column(name = "transfer_type", nullable = false)
	public Integer getTransferType() {
		return this.transferType;
	}

	public void setTransferType(Integer transferType) {
		this.transferType = transferType;
	}

	@Column(name = "transfer_status")
	public Integer getTransferStatus() {
		return this.transferStatus;
	}

	public void setTransferStatus(Integer transferStatus) {
		this.transferStatus = transferStatus;
	}

}