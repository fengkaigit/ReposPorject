package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Area entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "area")
public class Area implements java.io.Serializable {

	// Fields

	private String id;
	private String city;
	private String province;
	private String encodePath;
	private String namePath;

	// Constructors

	/** default constructor */
	public Area() {
	}

	/** minimal constructor */
	public Area(String id, String province, String encodePath) {
		this.id = id;
		this.province = province;
		this.encodePath = encodePath;
	}

	/** full constructor */
	public Area(String id, String city, String province, String encodePath,
			String namePath) {
		this.id = id;
		this.city = city;
		this.province = province;
		this.encodePath = encodePath;
		this.namePath = namePath;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 20)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "city", length = 24)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "province", nullable = false, length = 24)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "encode_path", nullable = false)
	public String getEncodePath() {
		return this.encodePath;
	}

	public void setEncodePath(String encodePath) {
		this.encodePath = encodePath;
	}

	@Column(name = "name_path")
	public String getNamePath() {
		return this.namePath;
	}

	public void setNamePath(String namePath) {
		this.namePath = namePath;
	}

}