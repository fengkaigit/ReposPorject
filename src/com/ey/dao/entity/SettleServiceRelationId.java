package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SettleProfitRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SettleServiceRelationId implements java.io.Serializable {

	// Fields

	private Long serviceBillId;
	private Long settleBillId;

	// Constructors

	/** default constructor */
	public SettleServiceRelationId() {
	}

	/** full constructor */
	public SettleServiceRelationId(Long serviceBillId, Long settleBillId) {
		this.serviceBillId = serviceBillId;
		this.settleBillId = settleBillId;
	}

	// Property accessors

	@Column(name = "service_bill_id", nullable = false)
	public Long getServiceBillId() {
		return this.serviceBillId;
	}

	public void setServiceBillId(Long serviceBillId) {
		this.serviceBillId = serviceBillId;
	}

	@Column(name = "settle_id", nullable = false)
	public Long getSettleBillId() {
		return this.settleBillId;
	}

	public void setSettleBillId(Long settleBillId) {
		this.settleBillId = settleBillId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SettleServiceRelationId))
			return false;
		SettleServiceRelationId castOther = (SettleServiceRelationId) other;

		return (this.getServiceBillId() == castOther.getServiceBillId())
				&& (this.getSettleBillId() == castOther.getSettleBillId());
	}
}