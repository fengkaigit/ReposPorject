package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BillModel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bill_model")
public class BillModel implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private String modelName;
	private String billCode;
	private Long entId;

	// Constructors

	/** default constructor */
	public BillModel() {
	}

	/** full constructor */
	public BillModel(Long id, Long userId, String modelName, String billCode,
			Long entId) {
		this.id = id;
		this.userId = userId;
		this.modelName = modelName;
		this.billCode = billCode;
		this.entId = entId;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "user_ID", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
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
	public Long getEntId() {
		return this.entId;
	}

	public void setEntId(Long entId) {
		this.entId = entId;
	}

}