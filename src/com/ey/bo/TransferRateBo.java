package com.ey.bo;

import com.ey.dao.entity.TransferRate;
import com.ey.dao.entity.TransferRateId;

public class TransferRateBo extends TransferRate {
    private String bankName;
    public TransferRateBo(){
    	
    }
    public TransferRateBo(String bankId,String bankName,Double limitMoney,Double rate){
    	super(new TransferRateId(bankId,limitMoney,rate));
    	this.bankName = bankName;
    }
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
    
}
