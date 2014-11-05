package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BaseCustomProp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_custom_prop")
public class BaseCustomProp implements java.io.Serializable {

	// Fields

	private String propEngName;
	private String propChName;

	// Constructors

	/** default constructor */
	public BaseCustomProp() {
	}

	/** full constructor */
	public BaseCustomProp(String propEngName, String propChName) {
		this.propEngName = propEngName;
		this.propChName = propChName;
	}

	// Property accessors
	@Id
	@Column(name = "prop_eng_name", unique = true, nullable = false, length = 90)
	public String getPropEngName() {
		return this.propEngName;
	}

	public void setPropEngName(String propEngName) {
		this.propEngName = propEngName;
	}

	@Column(name = "prop_ch_name", nullable = false, length = 90)
	public String getPropChName() {
		return this.propChName;
	}

	public void setPropChName(String propChName) {
		this.propChName = propChName;
	}

}