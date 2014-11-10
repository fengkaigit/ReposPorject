package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FeeRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fee_rule")
public class FeeRule implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private String profile;
	private String IExplain;
	private String rule;
	private Integer paymentType;
	private Date beginTime;
	private Date endTime;

	// Constructors

	/** default constructor */
	public FeeRule() {
	}

	/** minimal constructor */
	public FeeRule(Long id, Long userId, Integer paymentType, Date beginTime) {
		this.id = id;
		this.userId = userId;
		this.paymentType = paymentType;
		this.beginTime = beginTime;
	}

	/** full constructor */
	public FeeRule(Long id, Long userId, String profile, String IExplain,
			String rule, Integer paymentType, Date beginTime, Date endTime) {
		this.id = id;
		this.userId = userId;
		this.profile = profile;
		this.IExplain = IExplain;
		this.rule = rule;
		this.paymentType = paymentType;
		this.beginTime = beginTime;
		this.endTime = endTime;
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

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "profile", length = 250)
	public String getProfile() {
		return this.profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Column(name = "i_explain")
	public String getIExplain() {
		return this.IExplain;
	}

	public void setIExplain(String IExplain) {
		this.IExplain = IExplain;
	}

	@Column(name = "rule")
	public String getRule() {
		return this.rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	@Column(name = "payment_type", nullable = false)
	public Integer getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "begin_time", nullable = false, length = 10)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "end_time", length = 10)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}