package com.ey.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "payment_hedge")
public class PaymentHedge implements Serializable {
	private Long id;
	private Long billId;
	private Double hedgeMoney;
	private Date createTime;
	private Integer statisStatus;
	private Integer hedgeType;
	
	public PaymentHedge(){
		
	}
	
	public PaymentHedge(Long id, Long billId, Double hedgeMoney,
			Date createTime, Integer statisStatus, Integer hedgeType) {
		super();
		this.id = id;
		this.billId = billId;
		this.hedgeMoney = hedgeMoney;
		this.createTime = createTime;
		this.statisStatus = statisStatus;
		this.hedgeType = hedgeType;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "bill_id", nullable = false)
	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}
    
	@Column(name = "hedge_money")
	public Double getHedgeMoney() {
		return hedgeMoney;
	}

	public void setHedgeMoney(Double hedgeMoney) {
		this.hedgeMoney = hedgeMoney;
	}
    
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
	@Column(name = "statis_status")
	public Integer getStatisStatus() {
		return statisStatus;
	}

	public void setStatisStatus(Integer statisStatus) {
		this.statisStatus = statisStatus;
	}
    
	@Column(name = "hedge_type")
	public Integer getHedgeType() {
		return hedgeType;
	}

	public void setHedgeType(Integer hedgeType) {
		this.hedgeType = hedgeType;
	}

}
