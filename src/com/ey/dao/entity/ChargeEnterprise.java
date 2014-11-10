package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ChargeEnterprise entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "charge_enterprise")
public class ChargeEnterprise implements java.io.Serializable {

	// Fields

	private Long id;
	private Long areaId;
	private String enterpriseName;
	private String careNumber;
	private Integer payType;

	// Constructors

	/** default constructor */
	public ChargeEnterprise() {
	}

	/** minimal constructor */
	public ChargeEnterprise(Long id, Long areaId, String enterpriseName,
			Integer payType) {
		this.id = id;
		this.areaId = areaId;
		this.enterpriseName = enterpriseName;
		this.payType = payType;
	}

	/** full constructor */
	public ChargeEnterprise(Long id, Long areaId, String enterpriseName,
			String careNumber, Integer payType) {
		this.id = id;
		this.areaId = areaId;
		this.enterpriseName = enterpriseName;
		this.careNumber = careNumber;
		this.payType = payType;
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

	@Column(name = "area_id", nullable = false)
	public Long getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	@Column(name = "enterprise_name", nullable = false, length = 180)
	public String getEnterpriseName() {
		return this.enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	@Column(name = "care_number", length = 30)
	public String getCareNumber() {
		return this.careNumber;
	}

	public void setCareNumber(String careNumber) {
		this.careNumber = careNumber;
	}

	@Column(name = "pay_type", nullable = false)
	public Integer getPayType() {
		return this.payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

}