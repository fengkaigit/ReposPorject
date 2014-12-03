package com.ey.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BankAccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "payment_setting")
public class PaymentSetting implements java.io.Serializable {
	private Long id;
	private Long userId;
	private Integer groupId;
	private String groupName;
	private String areaId;
	private String areaName;
	private Integer paymentType;
	private String paymentTypeName;
	private Long entId;
	private String entName;
	private String billNumber;
	private String payAddress;
	private Date createTime;
	private Integer delFlag;
	private String hoster;
	private String vehicleNumber;
	private String carframeNumber;
	private String engineNumber;

	public PaymentSetting() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentSetting(Long id, Long userId, Integer groupId,
			String groupName, String areaId, String areaName,
			Integer paymentType, String paymentTypeName, Long entId,
			String entName, String billNumber, String payAddress,
			Date createTime, Integer delFlag, String hoster) {
		super();
		this.id = id;
		this.userId = userId;
		this.groupId = groupId;
		this.groupName = groupName;
		this.areaId = areaId;
		this.areaName = areaName;
		this.paymentType = paymentType;
		this.paymentTypeName = paymentTypeName;
		this.entId = entId;
		this.entName = entName;
		this.billNumber = billNumber;
		this.payAddress = payAddress;
		this.createTime = createTime;
		this.delFlag = delFlag;
		this.hoster = hoster;
	}
	
	public PaymentSetting(Long id, Long userId, Integer groupId,
			String groupName, String areaId, String areaName,
			Integer paymentType, String paymentTypeName, Long entId,
			String entName, String billNumber, String payAddress,
			Date createTime, Integer delFlag, String hoster, 
			String vehicleNumber, String carframeNumber, String engineNumber) {
		super();
		this.id = id;
		this.userId = userId;
		this.groupId = groupId;
		this.groupName = groupName;
		this.areaId = areaId;
		this.areaName = areaName;
		this.paymentType = paymentType;
		this.paymentTypeName = paymentTypeName;
		this.entId = entId;
		this.entName = entName;
		this.billNumber = billNumber;
		this.payAddress = payAddress;
		this.createTime = createTime;
		this.delFlag = delFlag;
		this.hoster = hoster;
		this.vehicleNumber = vehicleNumber;
		this.carframeNumber = carframeNumber;
		this.engineNumber = engineNumber;
	}

	@Column(name = "hoster")
	public String getHoster() {
		return hoster;
	}

	public void setHoster(String hoster) {
		this.hoster = hoster;
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

	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "group_id")
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	@Column(name = "group_name")
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "area_id")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Column(name = "area_name")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "payment_type")
	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	@Column(name = "payment_type_name")
	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	@Column(name = "ent_id")
	public Long getEntId() {
		return entId;
	}

	public void setEntId(Long entId) {
		this.entId = entId;
	}

	@Column(name = "ent_name")
	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	@Column(name = "bill_number")
	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	@Column(name = "pay_address")
	public String getPayAddress() {
		return payAddress;
	}

	public void setPayAddress(String payAddress) {
		this.payAddress = payAddress;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "vehicle_number")
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	@Column(name = "carframe_number")
	public String getCarframeNumber() {
		return carframeNumber;
	}

	public void setCarframeNumber(String carframeNumber) {
		this.carframeNumber = carframeNumber;
	}

	@Column(name = "engine_number")
	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	@Column(name = "del_flag")
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

}