package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TransferRate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "transfer_rate")
public class TransferRate implements java.io.Serializable {

	// Fields

	private TransferRateId id;

	// Constructors

	/** default constructor */
	public TransferRate() {
	}

	/** full constructor */
	public TransferRate(TransferRateId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankId", column = @Column(name = "bank_id", nullable = false)),
			@AttributeOverride(name = "limitMoney", column = @Column(name = "limit_money", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "rate", column = @Column(name = "rate", nullable = false, precision = 22, scale = 0)) })
	public TransferRateId getId() {
		return this.id;
	}

	public void setId(TransferRateId id) {
		this.id = id;
	}

}