package com.ey.bo;


public class BaseRuleFeeBo implements java.io.Serializable {
	private String areaId;
	private Integer paymentType;
	private Double personalPoundage;
	private Double unitPoundage;
	private String paymentTypeName;
	private String areaName;

	public BaseRuleFeeBo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseRuleFeeBo(String areaId, Integer paymentType,
			Double personalPoundage, Double unitPoundage) {
		super();
		this.areaId = areaId;
		this.paymentType = paymentType;
		this.personalPoundage = personalPoundage;
		this.unitPoundage = unitPoundage;
	}

	public BaseRuleFeeBo(String areaId, String areaName, Integer paymentType,
			Double personalPoundage, Double unitPoundage) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
		this.paymentType = paymentType;
		this.personalPoundage = personalPoundage;
		this.unitPoundage = unitPoundage;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Double getPersonalPoundage() {
		return personalPoundage;
	}

	public void setPersonalPoundage(Double personalPoundage) {
		this.personalPoundage = personalPoundage;
	}

	public Double getUnitPoundage() {
		return unitPoundage;
	}

	public void setUnitPoundage(Double unitPoundage) {
		this.unitPoundage = unitPoundage;
	}

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}