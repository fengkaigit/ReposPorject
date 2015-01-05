package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TransferRate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "transfer_rate")
public class TransferRate implements java.io.Serializable {

	// Fields
    private Long id;
    private String bankCode;
    private String bankName;
	private double computerLimit;
	private double mobileLimit;
	private double computerRate;
	private double mobileRate;


	// Constructors

	/** default constructor */
	public TransferRate() {
	}

	/** full constructor */
	public TransferRate(Long id, String bankCode, String bankName,
			double computerLimit, double mobileLimit, double computerRate,
			double mobileRate) {
		super();
		this.id = id;
		this.bankCode = bankCode;
		this.bankName = bankName;
		this.computerLimit = computerLimit;
		this.mobileLimit = mobileLimit;
		this.computerRate = computerRate;
		this.mobileRate = mobileRate;
	}
    
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	// Property accessors
    
	@Column(name = "bank_code")
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	@Column(name = "bank_name")
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	@Column(name = "computer_limit")
	public double getComputerLimit() {
		return computerLimit;
	}

	public void setComputerLimit(double computerLimit) {
		this.computerLimit = computerLimit;
	}
    
	@Column(name = "mobile_limit")
	public double getMobileLimit() {
		return mobileLimit;
	}

	public void setMobileLimit(double mobileLimit) {
		this.mobileLimit = mobileLimit;
	}
	
	@Column(name = "computer_rate")
	public double getComputerRate() {
		return computerRate;
	}

	public void setComputerRate(double computerRate) {
		this.computerRate = computerRate;
	}
     
	@Column(name = "mobile_rate")
	public double getMobileRate() {
		return mobileRate;
	}

	public void setMobileRate(double mobileRate) {
		this.mobileRate = mobileRate;
	}
    
	
}