package com.ey.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.dao.ProfitCalculateDAO;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.ProfitBill;
import com.ey.dao.entity.ProfitServiceRelation;
import com.ey.dao.entity.ProfitServiceRelationId;
import com.ey.dao.entity.ProfitTransferRelation;
import com.ey.dao.entity.ProfitTransferRelationId;
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
	public Integer changePaymentBillStatus() throws RuntimeException{ 
		Integer status = profitDao.updateStatus("PaymentBill", "paymentStatus", "divideStatus", 10, 1);
		return status;
	}
	

	@Override
	public void saveTempPaymentBillTask(PaymentBill paymentBill,UUID uuid) throws RuntimeException, IllegalAccessException, InvocationTargetException {
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
	public List<PaymentBill> findPaymentBillList(Integer paymentStatus,Integer divideStatus) throws RuntimeException {
		List<PaymentBill> lst = profitDao.findPaymentBills(paymentStatus,divideStatus);
		return lst;
	}


	@Override
	public Double createServiceChargeBill(Long serviceBillId) throws RuntimeException {
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
	public void updatePaymentBillUUID(UUID uuid) throws RuntimeException {
		profitDao.updatePaymentBillUUID(uuid);
	}


	@Override
	public void saveServicePaymentRelation(Long serviceBillId,
			List<PaymentBill> billList) throws RuntimeException {
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
	public void createTransferBill(Long serviceTransferBillId,Double profitMoney, Long outAccountId, Long inAccountId)
			throws RuntimeException {

		//�Ƿ���Ҫ�޸�ϵͳ�˻����е�������˻���������˻�������ӣ�ϵͳ���˻�����Ƿ���Ҫ���
		
		TransferRecords transferRecord = new TransferRecords();
		transferRecord.setId(serviceTransferBillId);
		transferRecord.setPoundage(0);
		transferRecord.setTransferMoney(profitMoney);
		transferRecord.setTransferOutAccountId(outAccountId);
		transferRecord.setTransferInAccountId(inAccountId);
		transferRecord.setTransferTime(new Date());
		transferRecord.setTransferStatus(0);
		transferRecord.setTransferType(11);
		profitDao.save(transferRecord);
		profitDao.updateStatus("TempPaymentBill", "stageStatus", "stageStatus", 3, 4);
		profitDao.updateStatus("PaymentBill", "divideStatus", "divideStatus", 3, 4);
	}


	@Override
	public void saveServiceTransferRecords(Long serviceBillId,
			Long transferAccountId) throws RuntimeException {
		ServiceTransferRelation relation = new ServiceTransferRelation();
		ServiceTransferRelationId relationId = new ServiceTransferRelationId(serviceBillId,transferAccountId);
		relation.setId(relationId);
		profitDao.save(relation);
		profitDao.updateStatus("TempPaymentBill", "stageStatus", "stageStatus", 4, 5);
		profitDao.updateStatus("PaymentBill", "divideStatus", "divideStatus", 4, 5);
	}


	@Override
	public void clearTempPaymentBill() throws RuntimeException {
		profitDao.deleteTempPaymentBill();
		profitDao.updateStatus("PaymentBill", "divideStatus", "divideStatus", 5, 6);
	}


	@Override
	public Double findTransferRecordsPoundage(Long serviceBillId)
			throws RuntimeException {
		Double poundage = profitDao.findTransferRecordsPoundage(serviceBillId);
		return poundage;
	}


	@Override
	public Double findServiceBillMoney(Long serviceBillId) throws RuntimeException {
		Double serviceMoney = profitDao.findServiceBillMoney(serviceBillId);
		return serviceMoney;
	}


	@Override
	public void saveProfitBill(Long profitBillId, Double profitMoney)
			throws RuntimeException {
		ProfitBill profitBill = new ProfitBill();
		profitBill.setId(profitBillId);
		profitBill.setProfitMoney(profitMoney);
		profitBill.setCreateDate(new Date());
		profitBill.setConfirmDate(new Date());
		profitBill.setStatus(2);
		profitDao.save(profitBill);
	}


	@Override
	public void saveServiceProfitRelation(Long serviceBillId, Long profitBillId)
			throws RuntimeException {
		ProfitServiceRelationId profitServiceId = new ProfitServiceRelationId();
		profitServiceId.setProfitBillId(profitBillId);
		profitServiceId.setServiceBillId(serviceBillId);
		ProfitServiceRelation profitServiceRelation = new ProfitServiceRelation(profitServiceId);
		profitDao.save(profitServiceRelation);
	}


	@Override
	public Long getSystemAccountId(Integer accountType) throws RuntimeException {
		Long accountId = profitDao.getAccountId(accountType);
		return accountId;
	}


	@Override
	public void saveProfitTransferRecords(Long profitBillId,
			Long transferAccountId) throws RuntimeException {
		ProfitTransferRelationId relationId = new ProfitTransferRelationId(profitBillId,transferAccountId);
		ProfitTransferRelation relation = new ProfitTransferRelation(relationId);
		profitDao.save(relation);
	}


	@Override
	public List<PaymentBill> findPaymentBillList(Long serviceBillId) throws RuntimeException {
		
		return profitDao.findPaymentBills(serviceBillId);
	}


	@Override
	public List<AgentInfo> findAgentInfo(List<PaymentBill> paymentList)
			throws RuntimeException {
		
		return profitDao.findAgentInfo(paymentList);
	}

}
