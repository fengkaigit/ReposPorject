package com.ey.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BankAccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bank_account")
public class BankAccount implements java.io.Serializable {

	// Fields

	private long id;
	private String bankId;
	private String cardNumber;
	private String cardName;
	private String bankDeposit;
	private Integer cardType;

	// Constructors

	/** default constructor */
	public BankAccount() {
	}

	/** minimal constructor */
	public BankAccount(long id, String bankId, String cardNumber,
			String cardName, Integer cardType) {
		this.id = id;
		this.bankId = bankId;
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.cardType = cardType;
	}

	/** full constructor */
	public BankAccount(long id, String bankId, String cardNumber,
			String cardName, String bankDeposit, Integer cardType) {
		this.id = id;
		this.bankId = bankId;
		this.cardNumber = cardNumber;
		this.cardName = cardName;
		this.bankDeposit = bankDeposit;
		this.cardType = cardType;
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

	@Column(name = "bank_ID", nullable = false, length = 10)
	public String getBankId() {
		return this.bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	@Column(name = "card_number", nullable = false, length = 30)
	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Column(name = "card_name", nullable = false, length = 180)
	public String getCardName() {
		return this.cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	@Column(name = "bank_deposit", length = 180)
	public String getBankDeposit() {
		return this.bankDeposit;
	}

	public void setBankDeposit(String bankDeposit) {
		this.bankDeposit = bankDeposit;
	}

	@Column(name = "card_type", nullable = false)
	public Integer getCardType() {
		return this.cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

}