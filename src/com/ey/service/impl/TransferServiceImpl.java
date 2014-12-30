package com.ey.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dao.TransferDAO;
import com.ey.dao.entity.TransferRate;
import com.ey.dao.entity.TransferRecords;
import com.ey.forms.JfForm;
import com.ey.service.ProfitCalculateService;
import com.ey.service.TransferService;
@Service("transferService")
public class TransferServiceImpl implements TransferService {
	@Autowired
	TransferDAO transferDAO;
	@Autowired
	ProfitCalculateService ProfitCalculateService;
	@Override
	public void saveTransferRecord(TransferRecords transferRecords) throws RuntimeException {
		transferDAO.saveTransferRecord(transferRecords);
	}
	@Override
	public void saveTransferRecord(JfForm form) throws RuntimeException {
		Integer ps = form.getPaymentStatus();
		if(ps!=null&&ps.intValue()==1){
			Long systemAccountId = ProfitCalculateService.getSystemAccountId(0);
			TransferRate tr = getTransferRateByBank(form.getBankCode());
			double poundage = 0;
			if(tr!=null){
				poundage = (form.getBillMoney()+form.getPoundage())*tr.getRate();
				if(poundage>tr.getLimitMoney()){
					poundage = tr.getLimitMoney();
				}
			}
			TransferRecords transferRecords = new TransferRecords(null, new Date(), poundage,
					form.getBillMoney()+form.getPoundage(), form.getUserId(),
					systemAccountId, 0,
					0);
			saveTransferRecord(transferRecords);
		}
	}
	@Override
	public TransferRate getTransferRateByBank(String bankId)
			throws RuntimeException {
		return transferDAO.getTransferRateByBank(bankId);
	}

}
