package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PersonTransferRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "person_transfer_relation")
public class PersonTransferRelation implements java.io.Serializable {

	// Fields

	private long receiptsId;
	private long transferRecordsId;

	// Constructors

	/** default constructor */
	public PersonTransferRelation() {
	}

	/** full constructor */
	public PersonTransferRelation(long receiptsId, long transferRecordsId) {
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