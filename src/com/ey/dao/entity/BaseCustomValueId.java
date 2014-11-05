package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BaseCustomValueId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class BaseCustomValueId implements java.io.Serializable {

	// Fields

	private String customEngName;
	private Integer dataValue;

	// Constructors

	/** default constructor */
	public BaseCustomValueId() {
	}

	/** full constructor */
	public BaseCustomValueId(String customEngName, Integer dataValue) {
		this.customEngName = customEngName;
		this.dataValue = dataValue;
	}

	// Property accessors

	@Column(name = "custom_eng_name", nullable = false, length = 90)
	public String getCustomEngName() {
		return this.customEngName;
	}

	public void setCustomEngName(String customEngName) {
		this.customEngName = customEngName;
	}

	@Column(name = "data_value", nullable = false)
	public Integer getDataValue() {
		return this.dataValue;
	}

	public void setDataValue(Integer dataValue) {
		this.dataValue = dataValue;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BaseCustomValueId))
			return false;
		BaseCustomValueId castOther = (BaseCustomValueId) other;

		return ((this.getCustomEngName() == castOther.getCustomEngName()) || (this
				.getCustomEngName() != null
				&& castOther.getCustomEngName() != null && this
				.getCustomEngName().equals(castOther.getCustomEngName())))
				&& ((this.getDataValue() == castOther.getDataValue()) || (this
						.getDataValue() != null
						&& castOther.getDataValue() != null && this
						.getDataValue().equals(castOther.getDataValue())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getCustomEngName() == null ? 0 : this.getCustomEngName()
						.hashCode());
		result = 37 * result
				+ (getDataValue() == null ? 0 : this.getDataValue().hashCode());
		return result;
	}

}