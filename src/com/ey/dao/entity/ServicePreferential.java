package com.ey.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "service_preferential")
public class ServicePreferential implements java.io.Serializable {
	private Long id;
	private String areaId;
	private Long userId;
	private Integer paymentType;
	private Date beginTime;
	private Date endTime;
	private Double preferentialMoney;
	
	public ServicePreferential() {
		super();
	}
	
	public ServicePreferential(Long id,String areaId, Long userId, Integer paymentType,
			Date beginTime, Date endTime, Double preferentialMoney) {
		super();
		this.id = id;
		this.areaId = areaId;
		this.userId = userId;
		this.paymentType = paymentType;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.preferentialMoney = preferentialMoney;
	}
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "area_id")
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name = "payment_type")
	public Integer getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}
	@Column(name = "begin_time")
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	@Column(name = "end_time")
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name = "preferential_money")
	public Double getPreferentialMoney() {
		return preferentialMoney;
	}
	public void setPreferentialMoney(Double preferentialMoney) {
		this.preferentialMoney = preferentialMoney;
	}
	
}