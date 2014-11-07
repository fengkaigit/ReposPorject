package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PaymentTransferRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class PaymentTransferRelationId implements java.io.Serializable {

	// Fields

	private long receiptsId;
	private long transferRecordsId;

	// Constructors

	/** default constructor */
	public PaymentTransferRelationId() {
	}

	/** full constructor */
	public PaymentTransferRelationId(long receiptsId, long transferRecordsId) {
		this.receiptsId = receiptsId;
		this.transferRecordsId = transferRecordsId;
	}

	// Property accessors

	@Column(name = "receipts_id", nullable = false)
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PaymentTransferRelationId))
			return false;
		PaymentTransferRelationId castOther = (PaymentTransferRelationId) other;

		return (this.getReceiptsId() == castOther.getReceiptsId())
				&& (this.getTransferRecordsId() == castOther
						.getTransferRecordsId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getReceiptsId();
		result = 37 * result + (int) this.getTransferRecordsId();
		return result;
	}

}