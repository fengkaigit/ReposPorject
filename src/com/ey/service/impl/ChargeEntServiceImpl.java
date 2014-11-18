package com.ey.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.bo.ChargeEntBo;
import com.ey.dao.AgentDAO;
import com.ey.dao.ChargeEntDAO;
import com.ey.dao.common.dbid.DbidGenerator;
import com.ey.dao.entity.ChargeEnterprise;
import com.ey.service.ChargeEntService;
import com.ey.util.StringUtil;

@Service("chargeEntService")
public class ChargeEntServiceImpl implements ChargeEntService {

	@Autowired
    private ChargeEntDAO chargeEntDAO;
	
	@Override
	public void saveChargeEnt(ChargeEnterprise chargeEnt) throws RuntimeException {
		// TODO Auto-generated method stub
		 if(chargeEnt.getId() == null)
			 chargeEnt.setId(DbidGenerator.getDbidGenerator().getNextId());
		 chargeEntDAO.saveOrUpdate(chargeEnt);
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

}
