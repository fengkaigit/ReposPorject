package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FeeRuleHis entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fee_rule_his")
public class FeeRuleHis implements java.io.Serializable {

	// Fields

	private long id;
	private long userId;
	private String profile;
	private String IExplain;
	private String rule;
	private Integer paymentType;

	// Constructors

	/** default constructor */
	public FeeRuleHis() {
	}

	/** minimal constructor */
	public FeeRuleHis(long id, long userId, Integer paymentType) {
		this.id = id;
		this.userId = userId;
		this.paymentType = paymentType;
	}

	/** full constructor */
	public FeeRuleHis(long id, long userId, String profile, String IExplain,
			String rule, Integer paymentType) {
		this.id = id;
		this.userId = userId;
		this.profile = profile;
		this.IExplain = IExplain;
		this.rule = rule;
		this.paymentType = paymentType;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "user_id", nullable = false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
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

}