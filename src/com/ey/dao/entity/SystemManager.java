package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SystemManager entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "system_manager")
public class SystemManager implements java.io.Serializable {

	// Fields

	private long id;
	private long managerName;
	private String passwd;
	private String EMail;
	private String mobilePhone;

	// Constructors

	/** default constructor */
	public SystemManager() {
	}

	/** minimal constructor */
	public SystemManager(long id, long managerName, String passwd) {
		this.id = id;
		this.managerName = managerName;
		this.passwd = passwd;
	}

	/** full constructor */
	public SystemManager(long id, long managerName, String passwd,
			String EMail, String mobilePhone) {
		this.id = id;
		this.managerName = managerName;
		this.passwd = passwd;
		this.EMail = EMail;
		this.mobilePhone = mobilePhone;
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

	@Column(name = "manager_name", nullable = false)
	public long getManagerName() {
		return this.managerName;
	}

	public void setManagerName(long managerName) {
		this.managerName = managerName;
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

	@Column(name = "mobile_phone", length = 50)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

}