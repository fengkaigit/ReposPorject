package com.ey.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.bo.ChargeEntBo;
import com.ey.dao.ChargeEntDAO;
import com.ey.dao.common.dbid.DbidGenerator;
import com.ey.dao.entity.BankAccount;
import com.ey.dao.entity.ChargeEnterprise;
import com.ey.dao.entity.EntAccountRelation;
import com.ey.dao.entity.EntAccountRelationId;
import com.ey.service.ChargeEntService;
import com.ey.util.StringUtil;

@Service("chargeEntService")
public class ChargeEntServiceImpl implements ChargeEntService {

	@Autowired
    private ChargeEntDAO chargeEntDAO;
	
	@Override
	public void saveChargeEnt(ChargeEnterprise chargeEnt,BankAccount bankAccount) throws RuntimeException {
		// TODO Auto-generated method stub
	     chargeEnt.setId(DbidGenerator.getDbidGenerator().getNextId());
	     chargeEnt.setCareNumber(bankAccount.getCardNumber());
		 chargeEntDAO.save(chargeEnt);
		 bankAccount.setId(DbidGenerator.getDbidGenerator().getNextId());
		 chargeEntDAO.save(bankAccount);
		 chargeEntDAO.save(new EntAccountRelation(new EntAccountRelationId(chargeEnt.getId(),bankAccount.getId()),false));
	}

	@Override
	public void deleteChargeEntByIds(String[] ids) throws RuntimeException {
		// TODO Auto-generated method stub
		for(String id:ids){
        	if(StringUtil.isEmptyString(id))
        		continue;
        	chargeEntDAO.deleteByEntId(Long.valueOf(id.trim()));
        }
	}

	@Override
	public List<ChargeEntBo> getAllChargeEnt(Map<String, Object> Qparam,
			Integer page, Integer rows) throws RuntimeException {
		// TODO Auto-generated method stub
		return chargeEntDAO.getAllChargeEnt(Qparam, page, rows);
	}

	@Override
	public ChargeEntBo getChargeEnt(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		return chargeEntDAO.getChargeEnt(id);
	}

	@Override
	public List<ChargeEntBo> getChargesByArea(String areaId) throws RuntimeException{
		// TODO Auto-generated method stub
		return chargeEntDAO.getChargesByArea(areaId);
	}

	@Override
	public List<ChargeEnterprise> getChargesByArea(String areaId, int payType) throws RuntimeException{
		// TODO Auto-generated method stub
		return chargeEntDAO.getChargesByArea(areaId,payType);
	}

	@Override
	public void updateChargeEnt(ChargeEnterprise chargeEnt,BankAccount bankAccount)
			throws RuntimeException {
		// TODO Auto-generated method stub
		chargeEnt.setCareNumber(bankAccount.getCardNumber());
		chargeEntDAO.update(chargeEnt);
		BankAccount account = (BankAccount)chargeEntDAO.get(BankAccount.class, bankAccount.getId());
		if(account.getCardNumber().equalsIgnoreCase(bankAccount.getCardNumber())){
			BeanUtils.copyProperties(bankAccount, account);
			chargeEntDAO.update(account);
		}
		else{
			 chargeEntDAO.update(new EntAccountRelation(new EntAccountRelationId(chargeEnt.getId(),bankAccount.getId()),true));
			 bankAccount.setId(DbidGenerator.getDbidGenerator().getNextId());
			 chargeEntDAO.save(bankAccount);
			 chargeEntDAO.save(new EntAccountRelation(new EntAccountRelationId(chargeEnt.getId(),bankAccount.getId()),false));
		}
	}

	@Override
	public ChargeEnterprise getChargeEnterprise(Long id)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return (ChargeEnterprise)chargeEntDAO.get(ChargeEnterprise.class, id);
	}

	@Override
	public BankAccount getBankAccount(Long id) throws RuntimeException {
		// TODO Auto-generated method stub
		return chargeEntDAO.getBankAccount(id);
	}

	@Override
	public Long getTotalByParam(Map<String, Object> Qparam)
			throws RuntimeException {
		// TODO Auto-generated method stub
		return chargeEntDAO.getTotalByParam(Qparam);
	}

}
