package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProfitAccountRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "profit_account_relation")
public class ProfitAccountRelation implements java.io.Serializable {

	// Fields

	private long receiptsId;
	private long transferRecordsId;

	// Constructors

	/** default constructor */
	public ProfitAccountRelation() {
	}

	/** full constructor */
	public ProfitAccountRelation(long receiptsId, long transferRecordsId) {
		this.receiptsId = receiptsId;
		this.transferRecordsId = transferRecordsId;
	}

	// Property accessors
	@Id
	@Column(name = "receipts_id", unique = true, nullable = false)
	public long getReceiptsId() {
		return this.receiptsId;
	}

	public void setReceiptsId(long receiptsId) {
		this.receiptsId = receiptsId;
	}

	@Column(name = "transfer_records_id", nullable = false)
	public long getTransferRecordsId() {
		return this.transferRecordsId;
	}

	public void setTransferRecordsId(long transferRecordsId) {
		this.transferRecordsId = transferRecordsId;
	}

}