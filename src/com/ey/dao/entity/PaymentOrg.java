package com.ey.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "payment_org")
public class PaymentOrg implements Serializable {
    private String payOrgCode;
    private String payOrgName;
    private Integer sort;
    
    public PaymentOrg(){
    	
    }
    
    
    public PaymentOrg(String payOrgCode, String payOrgName, Integer sort) {
		super();
		this.payOrgCode = payOrgCode;
		this.payOrgName = payOrgName;
		this.sort = sort;
	}
	@Id
	@Column(name = "payment_org_code", unique = true, nullable = false)
	public String getPayOrgCode() {
		return payOrgCode;
	}
	public void setPayOrgCode(String payOrgCode) {
		this.payOrgCode = payOrgCode;
	}
	@Column(name = "payment_org_name")
	public String getPayOrgName() {
		return payOrgName;
	}
	public void setPayOrgName(String payOrgName) {
		this.payOrgName = payOrgName;
	}
	@Column(name = "sort")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
    
    
}
