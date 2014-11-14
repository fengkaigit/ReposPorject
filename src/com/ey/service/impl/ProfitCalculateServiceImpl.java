package com.ey.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dao.ProfitCalculateDAO;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.ServiceChargeBill;
import com.ey.dao.entity.ServicePaymentRelation;
import com.ey.dao.entity.ServicePaymentRelationId;
import com.ey.dao.entity.ServiceTransferRelation;
import com.ey.dao.entity.ServiceTransferRelationId;
import com.ey.dao.entity.TempPaymentBill;
import com.ey.dao.entity.TransferRecords;
import com.ey.service.ProfitCalculateService;

@Service("ProfitCalculateService")
//@Transactional(propagation = Propagation.REQUIRED, readOnly = false,rollbackFor = RuntimeException.class)
public class ProfitCalculateServiceImpl implements ProfitCalculateService {

	@Autowired
	private ProfitCalculateDAO profitDao;

	@Override
	public Integer changePaymentBillStatus() { 
		Integer status = profitDao.updateStatus("PaymentBill", "paymentStatus", "divideStatus", 10, 1);
		return status;
	}
	

	@Override
	public void saveTempPaymentBillTask(PaymentBill paymentBill,UUID uuid) throws Exception {
		if (paymentBill!=null){
			TempPaymentBill tempPaymentBill = new TempPaymentBill();
			BeanUtils.copyProperties(tempPaymentBill,paymentBill);
			tempPaymentBill.setStageStatus(0);
			tempPaymentBill.setUuid(uuid.toString());
			profitDao.saveTempPaymentBill(tempPaymentBill);
			profitDao.updateStatus("TempPaymentBill", "stageStatus", "stageStatus", 0, 1);
		}
		
	}


	@Override
	public List<PaymentBill> findPaymentBillList(Integer paymentStatus,Integer divideStatus) {
		List<PaymentBill> lst = profitDao.findPaymentBills(paymentStatus,divideStatus);
		return lst;
	}


	@Override
	public Double createServiceChargeBill(Long serviceBillId) throws Exception {
		ServiceChargeBill serviceBill = new ServiceChargeBill();
		serviceBill.setId(serviceBillId);
		serviceBill.setCreateDate(new Date());
		serviceBill.setConfirmDate(new Date());
		Double profitMoney=profitDao.getProfitMoney();
		serviceBill.setProfitMoney(profitMoney);
		serviceBill.setStatus(2);
		profitDao.save(serviceBill);
		profitDao.updateStatus("TempPaymentBill", "stageStatus", "stageStatus", 1, 2);
		profitDao.updateStatus("PaymentBill", "divideStatus", "divideStatus", 1, 2);
		return profitMoney;
	}


	@Override
	public void updatePaymentBillUUID(UUID uuid) throws Exception {
		profitDao.updatePaymentBillUUID(uuid);
	}


	@Override
	public void saveServicePaymentRelation(Long serviceBillId,
			List<PaymentBill> billList) throws Exception {
		for(PaymentBill paymentBill:billList){
			ServicePaymentRelation relation = new ServicePaymentRelation();
			ServicePaymentRelationId relationId = new ServicePaymentRelationId(serviceBillId,paymentBill.getId());
			relation.setId(relationId);
			profitDao.save(relation);
		}
		profitDao.updateStatus("TempPaymentBill", "stageStatus", "stageStatus", 2, 3);
		profitDao.updateStatus("PaymentBill", "divideStatus", "divideStatus", 2, 3);
	}


	@Override
	public void createServiceTransferBill(Long serviceTransferBillId,Double profitMoney)
			throws Exception {
		//获取系统大账户ID
		Long systemAccountId = profitDao.getAccountId(1);
		if (systemAccountId==null)
			throw new Exception("未设置系统大账户"); 
		//获取系统劳务费账户ID
		Long serviceAccountId = profitDao.getAccountId(4);
		if (serviceAccountId==null)
			throw new Exception("未设置系统劳务费账户");
		
		//是否需要修改系统账户表中的劳务费账户余额，劳务费账户余额增加，系统大账户余额是否需要变更
		
		TransferRecords transferRecord = new TransferRecords();
		transferRecord.setId(serviceTransferBillId);
		transferRecord.setPoundage(0);
		transferRecord.setTransferMoney(profitMoney);
		transferRecord.setTransferOutAccountId(systemAccountId);
		transferRecord.setTransferInAccountId(serviceAccountId);
		transferRecord.setTransferTime(new Date());
		transferRecord.setTransferStatus(0);
		transferRecord.setTransferType(11);
		profitDao.save(transferRecord);
		profitDao.updateStatus("TempPaymentBill", "stageStatus", "stageStatus", 3, 4);
		profitDao.updateStatus("PaymentBill", "divideStatus", "divideStatus", 3, 4);
	}


	@Override
	public void saveServiceTransferRecords(Long serviceBillId,
			Long transferAccountId) throws Exception {
		ServiceTransferRelation relation = new ServiceTransferRelation();
		ServiceTransferRelationId relationId = new ServiceTransferRelationId(serviceBillId,transferAccountId);
		relation.setId(relationId);
		profitDao.save(relation);
		profitDao.updateStatus("TempPaymentBill", "stageStatus", "stageStatus", 4, 5);
		profitDao.updateStatus("PaymentBill", "divideStatus", "divideStatus", 4, 5);
	}


	@Override
	public void clearTempPaymentBill() throws Exception {
		profitDao.deleteTempPaymentBill();
		profitDao.updateStatus("PaymentBill", "divideStatus", "divideStatus", 5, 6);
	}

}
