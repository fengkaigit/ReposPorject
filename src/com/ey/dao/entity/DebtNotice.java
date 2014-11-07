package com.ey.dao.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * DebtNotice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "debt_notice")
public class DebtNotice implements java.io.Serializable {

	// Fields

	private DebtNoticeId id;

	// Constructors

	/** default constructor */
	public DebtNotice() {
	}

	/** full constructor */
	public DebtNotice(DebtNoticeId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "batchId", column = @Column(name = "batch_id", nullable = false)),
			@AttributeOverride(name = "paymentId", column = @Column(name = "payment_id", nullable = false)),
			@AttributeOverride(name = "billMoney", column = @Column(name = "bill_money", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "payMoney", column = @Column(name = "pay_money", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "debtMoney", column = @Column(name = "debt_money", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "noticeStatus", column = @Column(name = "notice_status", nullable = false)) })
	public DebtNoticeId getId() {
		return this.id;
	}

	public void setId(DebtNoticeId id) {
		this.id = id;
	}

}