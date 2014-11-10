package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EntTransferRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ent_transfer_relation")
public class EntTransferRelation implements java.io.Serializable {

	// Fields

	private Long receiptsId;
	private Long transferRecordsId;

	// Constructors

	/** default constructor */
	public EntTransferRelation() {
	}

	/** full constructor */
	public EntTransferRelation(Long receiptsId, Long transferRecordsId) {
		this.receiptsId = receiptsId;
		this.transferRecordsId = transferRecordsId;
	}

	// Property accessors
	@Id
	@Column(name = "receipts_id", unique = true, nullable = false)
	public Long getReceiptsId() {
		return this.receiptsId;
	}

	public void setReceiptsId(Long receiptsId) {
		this.receiptsId = receiptsId;
	}

	@Column(name = "transfer_records_id", nullable = false)
	public Long getTransferRecordsId() {
		return this.transferRecordsId;
	}

	public void setTransferRecordsId(Long transferRecordsId) {
		this.transferRecordsId = transferRecordsId;
	}

}