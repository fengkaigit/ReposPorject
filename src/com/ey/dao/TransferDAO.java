package com.ey.dao;

import com.ey.dao.base.BaseDAO;
import com.ey.dao.entity.TransferRate;
import com.ey.dao.entity.TransferRecords;

public interface TransferDAO extends BaseDAO {

	void saveTransferRecord(TransferRecords transferRecords);

	TransferRate getTransferRateByBank(String bankId);

}
