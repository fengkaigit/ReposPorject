package com.ey.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.bo.SystemAccountBo;
import com.ey.dao.SystemAccountDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.SystemAccount;

@Repository("systemAccountDAO")  
public class SystemAccountDAOImpl extends BaseDAOImpl implements SystemAccountDAO {

	@Override
	public List<SystemAccountBo> findSystemAccountList() throws RuntimeException {
		String hql = "select new com.ey.bo.SystemAccountBo(a.acctType,a.acctName,a.acctBalance,a.bankAccountId,b.cardNumber) " +
				"from SystemAccount a,BankAccount b where a.bankAccountId=b.id";
		return this.find(hql);
	}

	@Override
	public SystemAccountBo getSysAccount(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		String hql = "select new com.ey.bo.SystemAccountBo(a.acctType,a.acctName,a.acctBalance,a.bankAccountId,b.cardNumber) " +
				"from SystemAccount a,BankAccount b where a.bankAccountId=b.id and b.id=?";
		List<SystemAccountBo> lst = this.find(hql, new Object[]{id});
		if (lst!=null && lst.size()>0)
			return lst.get(0);
		else
			return null;
	}

}
