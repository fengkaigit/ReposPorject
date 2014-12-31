package com.ey.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dao.BankAccountDAO;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.BankCardInfo;
import com.ey.dao.entity.BankInfo;
import com.ey.forms.JfForm;
import com.ey.service.BankAccountService;
@Service("bankAccountService")
public class BankAccountServiceImpl implements BankAccountService {
	@Autowired
	private BankAccountDAO bankAccountDAO;

	@Override
	public void saveBankAccount(BankAccount bankAccount) throws RuntimeException {
		bankAccountDAO.saveBankAccount(bankAccount);
	}

	@Override
	public void saveBankAccount(JfForm form) {
		Integer ps = form.getPaymentStatus();
		if(ps!=null&&ps.intValue()==1){
		Long count = bankAccountDAO.getBankAccountNumber(form.getBankCode(),form.getBankAccount());
		if(count.longValue()>0){
			return;
		}
		BankInfo bankInfo = findBankInfoById(form.getBankCode());
		String bankName = form.getBankCode();
		if(bankInfo!=null){
			bankName = bankInfo.getBankName();
		}
		int cardType = 0;
		BankCardInfo cardInfo = findBankCardInfo(form.getBankCode(),form.getBankAccount());
		if(cardInfo!=null){
			cardType = cardInfo.getCardType();
		}
		BankAccount bankAccount = new BankAccount(null, form.getBankCode(), form.getBankAccount(),
				form.getBankAccount(), bankName, cardType);
		saveBankAccount(bankAccount);
		}
	}

	@Override
	public BankInfo findBankInfoById(String bankId) throws RuntimeException {
		return (BankInfo)bankAccountDAO.get(BankInfo.class,bankId);
	}

	@Override
	public BankCardInfo findBankCardInfo(String bankId, String cardNo) {
		// TODO Auto-generated method stub
		return bankAccountDAO.findBankCardInfo(bankId,cardNo);
	}

	@Override
	public List<BankInfo> getBankInfoList(Map<String, Object> params,
			Integer page, Integer rows) throws RuntimeException {
		
		return bankAccountDAO.getBankInfoList(params,page,rows);
	}
}
