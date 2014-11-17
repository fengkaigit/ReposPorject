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

	private Long id;
	private String managerName;
	private String passwd;
	private String EMail;
	private String mobilePhone;
	private String managerRealname;

	// Constructors

	/** default constructor */
	public SystemManager() {
	}

	/** minimal constructor */
	public SystemManager(Long id, String managerName, String passwd,
			String managerRealname) {
		this.id = id;
		this.managerName = managerName;
		this.passwd = passwd;
		this.managerRealname = managerRealname;
	}

	/** full constructor */
	public SystemManager(Long id, String managerName, String passwd,
			String EMail, String mobilePhone, String managerRealname) {
		this.id = id;
		this.managerName = managerName;
		this.passwd = passwd;
		this.EMail = EMail;
		this.mobilePhone = mobilePhone;
		this.managerRealname = managerRealname;
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

	@Column(name = "manager_name", nullable = false, length = 50)
	public String getManagerName() {
		return this.managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	@Column(name = "passwd", nullable = false, length = 150)
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

	@Column(name = "manager_realname", nullable = false, length = 50)
	public String getManagerRealname() {
		return this.managerRealname;
	}

	public void setManagerRealname(String managerRealname) {
		this.managerRealname = managerRealname;
	}

}