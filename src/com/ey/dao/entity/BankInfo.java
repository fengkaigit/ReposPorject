package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BankInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bank_info")
public class BankInfo implements java.io.Serializable {

	// Fields

	private String bankCode;
	private String bankName;

	// Constructors

	/** default constructor */
	public BankInfo() {
	}

	/** full constructor */
	public BankInfo(String bankCode, String bankName) {
		this.bankCode = bankCode;
		this.bankName = bankName;
	}

	// Property accessors
	@Id
	@Column(name = "bank_code", unique = true, nullable = false, length = 10)
	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Column(name = "bank_name", nullable = false, length = 180)
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}