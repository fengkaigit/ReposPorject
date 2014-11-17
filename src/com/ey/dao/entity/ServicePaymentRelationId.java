package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ServicePaymentRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class ServicePaymentRelationId implements java.io.Serializable {

	// Fields

	private Long serviceBillId;
	private Long paymentBillId;

	// Constructors

	/** default constructor */
	public ServicePaymentRelationId() {
	}

	/** full constructor */
	public ServicePaymentRelationId(Long serviceBillId, Long paymentBillId) {
		this.serviceBillId = serviceBillId;
		this.paymentBillId = paymentBillId;
	}

	// Property accessors

	@Column(name = "service_bill_id", nullable = false)
	public Long getServiceBillId() {
		return this.serviceBillId;
	}

	public void setServiceBillId(Long serviceBillId) {
		this.serviceBillId = serviceBillId;
	}

	@Column(name = "payment_bill_id", nullable = false)
	public Long getPaymentBillId() {
		return this.paymentBillId;
	}

	public void setPaymentBillId(Long paymentBillId) {
		this.paymentBillId = paymentBillId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ServicePaymentRelationId))
			return false;
		ServicePaymentRelationId castOther = (ServicePaymentRelationId) other;

		return (this.getServiceBillId() == castOther.getServiceBillId())
				&& (this.getPaymentBillId() == castOther.getPaymentBillId());
	}


}