package com.ey.bo;

import com.ey.dao.entity.SystemAccount;

public class SystemAccountBo extends SystemAccount {

	private String acctNo;
	private String memo;
	
	public SystemAccountBo() {
	}
	
	public SystemAccountBo(Integer acctType, String acctName, double acctBalance, Long bankAccountId, String acctNo){
		super(acctType,acctName,acctBalance,bankAccountId);
		this.acctNo = acctNo;
		if (acctType==0){
			this.memo="系统大账户:存放系统所有金额,系统的实际账户。";
		}else if (acctType==1){
			this.memo="系统收益账户:存放系统收益金额,即系统劳务费收入－系统手续费";
		}else if (acctType==2){
			this.memo="系统最终收益账户:存放系统最终盈利金额,即系统收益－系统垫付金额－代理商收益";
		}else if (acctType==3){
			this.memo="系统手续费账户:存放系统手续费金额,即用户缴费时,系统垫付的手续费";
		}else if (acctType==4){
			this.memo="系统劳务费账户:存放系统劳务费金额,即用户缴费时,支付的劳务费";
		}else if (acctType==5){
			this.memo="系统结算账户:存放系统所有代理商的收益金额";
		}else if (acctType==6){
			this.memo="系统垫付账户:存放系统所有垫付金额,包括垫付手续费、优惠活动垫付金额";
		}
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
