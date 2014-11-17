package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SysDbid entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_dbid")
public class SysDbid implements java.io.Serializable {

	// Fields

	private String key;
	private String value;

	// Constructors

	/** default constructor */
	public SysDbid() {
	}

	/** full constructor */
	public SysDbid(String key, String value) {
		this.key = key;
		this.value = value;
	}

	// Property accessors
    @Id
	@Column(name = "key_", nullable = false)
	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "value_", nullable = false)
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}