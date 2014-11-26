package com.ey.dao.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PaymentTraffic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "payment_traffic")
public class PaymentTraffic implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private Long modelId;
	private String carOwner;
	private Integer carType;
	private String carframeNumber;
	private String engineNumber;
	private double billMoney;
	private double poundage;
	private Date paymentTime;
	private String billNumber;

	// Constructors

	/** default constructor */
	public PaymentTraffic() {
	}

	/** minimal constructor */
	public PaymentTraffic(Long id, Long userId, Integer carType,
			double billMoney, Date paymentTime) {
		this.id = id;
		this.userId = userId;
		this.carType = carType;
		this.billMoney = billMoney;
		this.paymentTime = paymentTime;
	}

	/** full constructor */
	public PaymentTraffic(Long id, Long userId, Long modelId, String carOwner,
			Integer carType, String carframeNumber, String engineNumber,
			double billMoney, double poundage, Date paymentTime,
			String billNumber) {
		this.id = id;
		this.userId = userId;
		this.modelId = modelId;
		this.carOwner = carOwner;
		this.carType = carType;
		this.carframeNumber = carframeNumber;
		this.engineNumber = engineNumber;
		this.billMoney = billMoney;
		this.poundage = poundage;
		this.paymentTime = paymentTime;
		this.billNumber = billNumber;
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

	@Column(name = "user_id", nullable = false)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "model_id")
	public Long getModelId() {
		return this.modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	@Column(name = "car_owner", length = 50)
	public String getCarOwner() {
		return this.carOwner;
	}

	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}

	@Column(name = "car_type", nullable = false)
	public Integer getCarType() {
		return this.carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	@Column(name = "carframe_number", length = 50)
	public String getCarframeNumber() {
		return this.carframeNumber;
	}

	public void setCarframeNumber(String carframeNumber) {
		this.carframeNumber = carframeNumber;
	}

	@Column(name = "engine_number", length = 50)
	public String getEngineNumber() {
		return this.engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	@Column(name = "bill_money", nullable = false, precision = 22, scale = 0)
	public double getBillMoney() {
		return this.billMoney;
	}

	public void setBillMoney(double billMoney) {
		this.billMoney = billMoney;
	}

	@Column(name = "poundage", precision = 22, scale = 0)
	public double getPoundage() {
		return this.poundage;
	}

	public void setPoundage(double poundage) {
		this.poundage = poundage;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "payment_time", nullable = false, length = 10)
	public Date getPaymentTime() {
		return this.paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	@Column(name = "bill_number", length = 30)
	public String getBillNumber() {
		return this.billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

}