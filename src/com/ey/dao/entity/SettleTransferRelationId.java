package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SettleTransferRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SettleTransferRelationId implements java.io.Serializable {

	// Fields

	private Long receiptsId;
	private Long transferRecordsId;

	// Constructors

	/** default constructor */
	public SettleTransferRelationId() {
	}

	/** full constructor */
	public SettleTransferRelationId(Long receiptsId, Long transferRecordsId) {
		this.receiptsId = receiptsId;
		this.transferRecordsId = transferRecordsId;
	}

	// Property accessors

	@Column(name = "receipts_id", nullable = false)
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SettleTransferRelationId))
			return false;
		SettleTransferRelationId castOther = (SettleTransferRelationId) other;

		return (this.getReceiptsId() == castOther.getReceiptsId())
				&& (this.getTransferRecordsId() == castOther
						.getTransferRecordsId());
	}
}