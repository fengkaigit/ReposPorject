package com.ey.bo;

import com.ey.dao.entity.TransferRate;
import com.ey.dao.entity.TransferRateId;

public class TransferRateBo extends TransferRate {
    private String bankName;
    public TransferRateBo(){
    	
    }
    public TransferRateBo(Long id,String bankId,String bankName,Double limitMoney,Double rate,Boolean cityFlag, Boolean peerFlag){
    	super(id,bankId,limitMoney,rate,cityFlag,peerFlag);
    	this.bankName = bankName;
    }
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
    
}
