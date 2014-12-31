package com.ey.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ey.dao.BankAccountDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.BankCardInfo;
import com.ey.dao.entity.BankInfo;
@Repository("bankAccountDAO")
public class BankAccountDAOImpl extends BaseDAOImpl implements BankAccountDAO {

	@Override
	public void saveBankAccount(BankAccount bankAccount) {
		if (bankAccount.getId() == null) {
			super.getDbId(bankAccount);
		}
		saveOrUpdate(bankAccount);
	}

	@Override
	public BankCardInfo findBankCardInfo(String bankId, String cardNo) {
		if(cardNo!=null&&cardNo.length()>6){
			cardNo = cardNo.substring(0,6);
		}
		String hql = "from BankCardInfo where bankId=? and cardNumber=?";
		List<BankCardInfo> list = find(hql,new Object[]{bankId,cardNo});
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public Long getBankAccountNumber(String bankCode, String bankAccount) {
		String hql = "select count(id) from BankAccount where bankId=? and cardNumber=?";
		List<Object> list = find(hql, new Object[] { bankCode, bankAccount});
		if (list != null && list.size() > 0) {
			return new Long(list.get(0) + "");
		} else {
			return 0l;
		}
	}

	@Override
	public List<BankInfo> getBankInfoList(Map<String, Object> params,
			Integer page, Integer rows) throws RuntimeException {
		String hql = "from ";
		return null;
	}

}
