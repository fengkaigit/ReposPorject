package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SystemAccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "system_account")
public class SystemAccount implements java.io.Serializable {

	// Fields

	private Integer acctType;
	private String acctName;
	private double acctBalance;
	private Long bankAccountId;

	// Constructors

	/** default constructor */
	public SystemAccount() {
	}

	/** minimal constructor */
	public SystemAccount(Integer acctType, String acctName, double acctBalance) {
		this.acctType = acctType;
		this.acctName = acctName;
		this.acctBalance = acctBalance;
	}

	/** full constructor */
	public SystemAccount(Integer acctType, String acctName, double acctBalance,
			Long bankAccountId) {
		this.acctType = acctType;
		this.acctName = acctName;
		this.acctBalance = acctBalance;
		this.bankAccountId = bankAccountId;
	}

	// Property accessors
	@Id
	@Column(name = "acct_type", unique = true, nullable = false)
	public Integer getAcctType() {
		return this.acctType;
	}

	public void setAcctType(Integer acctType) {
		this.acctType = acctType;
	}

	@Column(name = "acct_name", nullable = false, length = 60)
	public String getAcctName() {
		return this.acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	@Column(name = "acct_balance", nullable = false, precision = 22, scale = 0)
	public double getAcctBalance() {
		return this.acctBalance;
	}

	public void setAcctBalance(double acctBalance) {
		this.acctBalance = acctBalance;
	}

	@Column(name = "bank_account_id")
	public Long getBankAccountId() {
		return this.bankAccountId;
	}

	public void setBankAccountId(Long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

}