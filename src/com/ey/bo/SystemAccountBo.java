package com.ey.bo;

import com.ey.dao.entity.SystemAccount;

public class SystemAccountBo extends SystemAccount {

	private String acctNo;
	
	public SystemAccountBo() {
	}
	
	public SystemAccountBo(Integer acctType, String acctName, double acctBalance, Long bankAccountId, String acctNo){
		super(acctType,acctName,acctBalance,bankAccountId);
		this.acctNo = acctNo;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	
	
}
