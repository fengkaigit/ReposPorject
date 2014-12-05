package com.ey.dao;

import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.BankCardInfo;

public interface BankAccountDAO extends BaseDAO {

	void saveBankAccount(BankAccount bankAccount);

	BankCardInfo findBankCardInfo(String bankId, String cardNo);

	Long getBankAccountNumber(String bankCode, String bankAccount);

}
