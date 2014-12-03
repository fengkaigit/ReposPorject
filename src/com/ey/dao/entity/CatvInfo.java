package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * NoticeInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "catv_info")
public class CatvInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String areaId;
	private Integer televisionType;
	private String televisionName;
	private double televisionMoney;

	// Constructors

	/** default constructor */
	public CatvInfo() {
	}

	/** full constructor */
	public CatvInfo(Long id, String areaId, Integer televisionType,
			String televisionName,double televisionMoney) {
		this.id = id;
		this.areaId = areaId;
		this.televisionType = televisionType;
		this.televisionName = televisionName;
		this.televisionMoney = televisionMoney;
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

	@Column(name = "area_id", nullable = false, length=20)
	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Column(name = "television_type", nullable = false)
	public Integer getTelevisionType() {
		return this.televisionType;
	}

	public void setTelevisionType(Integer televisionType) {
		this.televisionType = televisionType;
	}
	
	@Column(name = "television_name", nullable = false, length=150)
	public String getTelevisionName() {
		return this.televisionName;
	}

	public void setTelevisionName(String televisionName) {
		this.televisionName = televisionName;
	}
	
	@Column(name = "television_money", nullable = false)
	public double getTelevisionMoney() {
		return this.televisionMoney;
	}

	public void setTelevisionMoney(double televisionMoney) {
		this.televisionMoney = televisionMoney;
	}
}