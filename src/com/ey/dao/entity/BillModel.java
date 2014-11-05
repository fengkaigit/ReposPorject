package com.ey.dao.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * BillModel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bill_model")
public class BillModel implements java.io.Serializable {

	// Fields

	private long id;
	private long userId;
	private String modelName;
	private String billCode;
	private long entId;
	private Set<PaymentGas> paymentGases = new HashSet<PaymentGas>(0);

	// Constructors

	/** default constructor */
	public BillModel() {
	}

	/** minimal constructor */
	public BillModel(long id, long userId, String modelName, String billCode,
			long entId) {
		this.id = id;
		this.userId = userId;
		this.modelName = modelName;
		this.billCode = billCode;
		this.entId = entId;
	}

	/** full constructor */
	public BillModel(long id, long userId, String modelName, String billCode,
			long entId, Set<PaymentGas> paymentGases) {
		this.id = id;
		this.userId = userId;
		this.modelName = modelName;
		this.billCode = billCode;
		this.entId = entId;
		this.paymentGases = paymentGases;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "user_ID", nullable = false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "model_name", nullable = false, length = 60)
	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	@Column(name = "bill_code", nullable = false, length = 50)
	public String getBillCode() {
		return this.billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	@Column(name = "ent_id", nullable = false)
	public long getEntId() {
		return this.entId;
	}

	public void setEntId(long entId) {
		this.entId = entId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "billModel")
	public Set<PaymentGas> getPaymentGases() {
		return this.paymentGases;
	}

	public void setPaymentGases(Set<PaymentGas> paymentGases) {
		this.paymentGases = paymentGases;
	}

}