package com.ey.dao;

import java.util.List;
import java.util.Map;

import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.BankCardInfo;
import com.ey.dao.entity.BankInfo;

public interface BankAccountDAO extends BaseDAO {

	void saveBankAccount(BankAccount bankAccount);

	BankCardInfo findBankCardInfo(String bankId, String cardNo);

	Long getBankAccountNumber(String bankCode, String bankAccount);
	
	public List<BankInfo> getBankInfoList(Map<String, Object> params,
			Integer page, Integer rows) throws RuntimeException;

}
