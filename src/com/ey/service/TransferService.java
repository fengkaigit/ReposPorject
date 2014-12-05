package com.ey.service;

import com.ey.dao.entity.TransferRate;
import com.ey.dao.entity.TransferRecords;
import com.ey.forms.JfForm;

public interface TransferService {
	public void saveTransferRecord(TransferRecords transferRecords) throws RuntimeException;

	public void saveTransferRecord(JfForm form) throws RuntimeException;
	public TransferRate getTransferRateByBank(String bankId) throws RuntimeException;
}
