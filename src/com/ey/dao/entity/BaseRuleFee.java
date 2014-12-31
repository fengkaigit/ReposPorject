package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "base_rule_fee")
public class BaseRuleFee implements java.io.Serializable {
	private String areaId;
	private Integer paymentType;
	private Double personalPoundage;
	private Double unitPoundage;
	public BaseRuleFee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BaseRuleFee(String areaId, Integer paymentType,
			Double personalPoundage, Double unitPoundage) {
		super();
		this.areaId = areaId;
		this.paymentType = paymentType;
		this.personalPoundage = personalPoundage;
		this.unitPoundage = unitPoundage;
	}
	@Id
	@Column(name = "area_id", nullable = false)
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	@Id
	@Column(name = "payment_type", nullable = false)
	public Integer getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}
	@Column(name = "personalpoundage")
	public Double getPersonalPoundage() {
		return personalPoundage;
	}
	public void setPersonalPoundage(Double personalPoundage) {
		this.personalPoundage = personalPoundage;
	}
	@Column(name = "unitpoundage")
	public Double getUnitPoundage() {
		return unitPoundage;
	}
	public void setUnitPoundage(Double unitPoundage) {
		this.unitPoundage = unitPoundage;
	}
	
}