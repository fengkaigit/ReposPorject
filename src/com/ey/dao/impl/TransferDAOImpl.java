package com.ey.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ey.dao.TransferDAO;
import com.ey.dao.base.impl.BaseDAOImpl;
import com.ey.dao.entity.TransferRate;
import com.ey.dao.entity.TransferRecords;
@Repository("transferDAO")
public class TransferDAOImpl extends BaseDAOImpl implements TransferDAO {

	@Override
	public void saveTransferRecord(TransferRecords transferRecords) {
		if (transferRecords.getId() == null) {
			super.getDbId(transferRecords);
		}
		saveOrUpdate(transferRecords);
	}

	@Override
	public TransferRate getTransferRateByBank(String bankId) {
		// TODO Auto-generated method stub
		String hql = "from TransferRate where bankCode=?";
		List<TransferRate> rateList = find(hql,new Object[]{bankId});
		if(rateList!=null&&rateList.size()>0){
			return rateList.get(0);
		}
		return null;
	}

}
