package com.ey.service;

import java.util.List;
import java.util.Map;

import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.BankCardInfo;
import com.ey.dao.entity.BankInfo;
import com.ey.forms.JfForm;

public interface BankAccountService {
	public void saveBankAccount(BankAccount bankAccount) throws RuntimeException;

	public void saveBankAccount(JfForm form)throws RuntimeException;
	public BankInfo findBankInfoById(String bankId)throws RuntimeException;
	public BankCardInfo findBankCardInfo(String bankId,String cardNo);
	public List<BankInfo> getBankInfoList(Map<String,Object> params,Integer page,Integer rows) throws RuntimeException;
}
