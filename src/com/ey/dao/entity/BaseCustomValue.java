package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BaseCustomValue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "base_custom_value")
public class BaseCustomValue implements java.io.Serializable {

	// Fields

	private BaseCustomValueId id;
	private String propEngName;
	private String propChName;

	// Constructors

	/** default constructor */
	public BaseCustomValue() {
	}

	/** full constructor */
	public BaseCustomValue(BaseCustomValueId id, String propEngName,
			String propChName) {
		this.id = id;
		this.propEngName = propEngName;
		this.propChName = propChName;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "customEngName", column = @Column(name = "custom_eng_name", nullable = false, length = 90)),
			@AttributeOverride(name = "dataValue", column = @Column(name = "data_value", nullable = false)) })
	public BaseCustomValueId getId() {
		return this.id;
	}

	public void setId(BaseCustomValueId id) {
		this.id = id;
	}

	@Column(name = "prop_eng_name", nullable = false, length = 50)
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