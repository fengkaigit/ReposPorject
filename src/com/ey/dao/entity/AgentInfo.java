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
@Table(name = "agent_info", uniqueConstraints = @UniqueConstraint(columnNames = "regist_account"))
public class AgentInfo implements java.io.Serializable {

	// Fields

	private Long id;
	private String registAccount;
	private String passwd;
	private String EMail;
	private String mobile;
	private String areaId;
	private double rebackDot;
	private String registRealName;
	private Boolean delFlag;
	

	// Constructors

	/** default constructor */
	public AgentInfo() {
	}

	/** minimal constructor */
	public AgentInfo(Long id, String registAccount, String passwd, String areaId, double rebackDot) {
		this.id = id;
		this.registAccount = registAccount;
		this.passwd = passwd;
		this.areaId = areaId;
		this.rebackDot = rebackDot;
	}

	/** full constructor */
	public AgentInfo(Long id, String registAccount, String passwd,
			String EMail, String mobile, String areaId, double rebackDot,String registRealName) {
		this.id = id;
		this.registAccount = registAccount;
		this.passwd = passwd;
		this.EMail = EMail;
		this.mobile = mobile;
		this.areaId = areaId;
		this.rebackDot = rebackDot;
		this.registRealName = registRealName;
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

	@Column(name = "regist_account", unique = true, nullable = false, length = 50)
	public String getRegistAccount() {
		return this.registAccount;
	}

	public void setRegistAccount(String registAccount) {
		this.registAccount = registAccount;
	}

	@Column(name = "passwd", nullable = false, length = 50)
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
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "area_ID", nullable = false, length = 20)
	public String getAreaId() {
		return this.areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Column(name = "reback_dot", nullable = false, precision = 10, scale = 4)
	public double getRebackDot() {
		return this.rebackDot;
	}

	public void setRebackDot(double rebackDot) {
		this.rebackDot = rebackDot;
	}

	@Column(name = "regist_real_name",length = 200)
	public String getRegistRealName() {
		return registRealName;
	}
   
	public void setRegistRealName(String registRealName) {
		this.registRealName = registRealName;
	}
	@Column(name = "del_flag")
	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
	
	
	
}