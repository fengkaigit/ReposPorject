package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * UserBase entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_base", uniqueConstraints = @UniqueConstraint(columnNames = "account_number"))
public class UserBase implements java.io.Serializable {

	// Fields

	private Long id;
	private String areaId;
	private String accountNumber;
	private String realName;
	private Integer registType;
	private String passwd;
	private String EMail;
	private String mobilePhone;
	private double accountScore;

	// Constructors

	/** default constructor */
	public UserBase() {
	}

	/** minimal constructor */
	public UserBase(Long id, String areaId, String accountNumber,
			String realName, Integer registType, String passwd,
			double accountScore) {
		this.id = id;
		this.areaId = areaId;
		this.accountNumber = accountNumber;
		this.realName = realName;
		this.registType = registType;
		this.passwd = passwd;
		this.accountScore = accountScore;
	}

	/** full constructor */
	public UserBase(Long id, String areaId, String accountNumber,
			String realName, Integer registType, String passwd, String EMail,
			String mobilePhone, double accountScore) {
		this.id = id;
		this.areaId = areaId;
		this.accountNumber = accountNumber;
		this.realName = realName;
		this.registType = registType;
		this.passwd = passwd;
		this.EMail = EMail;
		this.mobilePhone = mobilePhone;
		this.accountScore = accountScore;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "area_ID", nullable = false, length = 20)
	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Column(name = "account_number", unique = true, nullable = false, length = 50)
	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Column(name = "real_name", nullable = false, length = 60)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "regist_type", nullable = false)
	public Integer getRegistType() {
		return this.registType;
	}

	public void setRegistType(Integer registType) {
		this.registType = registType;
	}

	@Column(name = "passwd", nullable = false, length = 150)
	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Column(name = "e_mail", length = 30)
	public String getEMail() {
		return this.EMail;
	}

	public void setEMail(String EMail) {
		this.EMail = EMail;
	}

	@Column(name = "mobile_phone", length = 15)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "account_score", nullable = false, precision = 22, scale = 0)
	public double getAccountScore() {
		return this.accountScore;
	}

	public void setAccountScore(double accountScore) {
		this.accountScore = accountScore;
	}

}