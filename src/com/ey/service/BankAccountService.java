package com.ey.service;

import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.BankCardInfo;
import com.ey.dao.entity.BankInfo;
import com.ey.forms.JfForm;

public interface BankAccountService {
	public void saveBankAccount(BankAccount bankAccount) throws RuntimeException;

	public void saveBankAccount(JfForm form)throws RuntimeException;
	public BankInfo findBankInfoById(String bankId)throws RuntimeException;
	public BankCardInfo findBankCardInfo(String bankId,String cardNo);
}
