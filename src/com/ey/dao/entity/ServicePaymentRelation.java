package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ServicePaymentRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "service_payment_relation")
public class ServicePaymentRelation implements java.io.Serializable {

	// Fields

	private ServicePaymentRelationId id;

	// Constructors

	/** default constructor */
	public ServicePaymentRelation() {
	}

	/** full constructor */
	public ServicePaymentRelation(ServicePaymentRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "serviceBillId", column = @Column(name = "service_bill_id", nullable = false)),
			@AttributeOverride(name = "paymentBillId", column = @Column(name = "payment_bill_id", nullable = false)) })
	public ServicePaymentRelationId getId() {
		return this.id;
	}

	public void setId(ServicePaymentRelationId id) {
		this.id = id;
	}

}