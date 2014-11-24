package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BankCardInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bank_card_info")
public class BankCardInfo implements java.io.Serializable {

	// Fields

	private Long bankId;
	private String cardNumber;
	private Integer cardType;

	// Constructors

	/** default constructor */
	public BankCardInfo() {
	}

	/** full constructor */
	public BankCardInfo(Long bankId, String cardNumber, Integer cardType) {
		this.bankId = bankId;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
	}

	// Property accessors
	@Id
	@Column(name = "bank_id", unique = true, nullable = false)
	public Long getBankId() {
		return this.bankId;
	}

	public void setBankCode(Long bankId) {
		this.bankId = bankId;
	}

	@Column(name = "card_number", nullable = false, length = 10)
	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	@Column(name = "card_type", nullable = false)
	public Integer getCardType() {
		return this.cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

}