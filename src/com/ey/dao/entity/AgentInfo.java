package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * AgentInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agent_info")
public class AgentInfo implements java.io.Serializable {

	// Fields

	private long id;
	private String registAccount;
	private String passwd;
	private String EMail;
	private Integer mobile;
	private String areaId;

	// Constructors

	/** default constructor */
	public AgentInfo() {
	}

	/** minimal constructor */
	public AgentInfo(long id, String registAccount, String passwd, String areaId) {
		this.id = id;
		this.registAccount = registAccount;
		this.passwd = passwd;
		this.areaId = areaId;
	}

	/** full constructor */
	public AgentInfo(long id, String registAccount, String passwd,
			String EMail, Integer mobile, String areaId) {
		this.id = id;
		this.registAccount = registAccount;
		this.passwd = passwd;
		this.EMail = EMail;
		this.mobile = mobile;
		this.areaId = areaId;
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

	@Column(name = "regist_account", unique = true, nullable = false, length = 50)
	public String getRegistAccount() {
		return this.registAccount;
	}

	public void setRegistAccount(String registAccount) {
		this.registAccount = registAccount;
	}

	@Column(name = "passwd", nullable = false, length = 20)
	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Column(name = "e_mail", length = 50)
	public String getEMail() {
		return this.EMail;
	}

	public void setEMail(String EMail) {
		this.EMail = EMail;
	}

	@Column(name = "mobile")
	public Integer getMobile() {
		return this.mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

	@Column(name = "area_ID", nullable = false, length = 20)
	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

}