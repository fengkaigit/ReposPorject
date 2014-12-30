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
    private String bankId;
	private double limitMoney;
	private double rate;
	private Boolean cityFlag;
	private Boolean peerFlag;

	// Constructors

	/** default constructor */
	public TransferRate() {
	}

	/** full constructor */
	public TransferRate(String bankId, double limitMoney, double rate,
			Boolean cityFlag, Boolean peerFlag) {
		super();
		this.bankId = bankId;
		this.limitMoney = limitMoney;
		this.rate = rate;
		this.cityFlag = cityFlag;
		this.peerFlag = peerFlag;
	}
	public TransferRate(Long id,String bankId, double limitMoney, double rate,
			Boolean cityFlag, Boolean peerFlag) {
		super();
		this.id = id;
		this.bankId = bankId;
		this.limitMoney = limitMoney;
		this.rate = rate;
		this.cityFlag = cityFlag;
		this.peerFlag = peerFlag;
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
	@Column(name = "bank_id", nullable = false, length = 10)
	public String getBankId() {
		return this.bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	@Column(name = "limit_money", nullable = false, precision = 22, scale = 0)
	public double getLimitMoney() {
		return this.limitMoney;
	}

	public void setLimitMoney(double limitMoney) {
		this.limitMoney = limitMoney;
	}

	@Column(name = "rate", nullable = false, precision = 22, scale = 0)
	public double getRate() {
		return this.rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	@Column(name = "city_flag")
	public Boolean getCityFlag() {
		return cityFlag;
	}

	public void setCityFlag(Boolean cityFlag) {
		this.cityFlag = cityFlag;
	}
	@Column(name = "peer_flag")
	public Boolean getPeerFlag() {
		return peerFlag;
	}

	public void setPeerFlag(Boolean peerFlag) {
		this.peerFlag = peerFlag;
	}
	

}