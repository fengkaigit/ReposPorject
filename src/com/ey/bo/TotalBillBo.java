package com.ey.bo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TotalBillBo implements Serializable {
     private Long batchId;
     private String payTypeName; 
     private String areaId;
     private String areaName;
     private Date createTime;
     private Long outBillNum;
     private Long errBillNum;
     private String batchIds;
     private Integer payType;
     public TotalBillBo(){
    	 
     }
    		 
     
	public TotalBillBo(Long batchId, Integer payType,String payTypeName, String areaId,
			String areaName, Date createTime, Long outBillNum) {
		super();
		this.batchId = batchId;
		this.payType = payType;
		this.payTypeName = payTypeName;
		this.areaId = areaId;
		this.areaName = areaName;
		this.createTime = createTime;
		this.outBillNum = outBillNum;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public String getPayTypeName() {
		return payTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getOutBillNum() {
		return outBillNum;
	}
	public void setOutBillNum(Long outBillNum) {
		this.outBillNum = outBillNum;
	}


	public String getBatchIds() {
		return batchIds;
	}


	public void setBatchIds(String batchIds) {
		this.batchIds = batchIds;
	}


	public Integer getPayType() {
		return payType;
	}


	public void setPayType(Integer payType) {
		this.payType = payType;
	}


	public Long getErrBillNum() {
		return errBillNum;
	}


	public void setErrBillNum(Long errBillNum) {
		this.errBillNum = errBillNum;
	}
    
     
}
