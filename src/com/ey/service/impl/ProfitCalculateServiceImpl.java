package com.ey.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.bo.AgentBo;
import com.ey.bo.PaymentAgentBo;
import com.ey.dao.ProfitCalculateDAO;
import com.ey.dao.common.dbid.DbidGenerator;
import com.ey.dao.entity.AgentInfo;
import com.ey.dao.entity.IncomeBill;
import com.ey.dao.entity.IncomeServiceRelation;
import com.ey.dao.entity.IncomeServiceRelationId;
import com.ey.dao.entity.IncomeTransferRelation;
import com.ey.dao.entity.IncomeTransferRelationId;
import com.ey.dao.entity.PaymentBill;
import com.ey.dao.entity.PoundageBill;
import com.ey.dao.entity.PoundageServiceRelation;
import com.ey.dao.entity.PoundageServiceRelationId;
import com.ey.dao.entity.PoundageTransferRelation;
import com.ey.dao.entity.PoundageTransferRelationId;
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
import com.ey.dao.entity.SettleBill;
import com.ey.dao.entity.SettleServiceRelation;
import com.ey.dao.entity.SettleServiceRelationId;
import com.ey.dao.entity.SettleTransferRelation;
import com.ey.dao.entity.SettleTransferRelationId;
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
		Integer status = profitDao.updateStatus("PaymentBill", "paymentStatus", "divideStatus", 10, 1, " and divideStatus=0 and (uuid is null or uuid='')");
		return status;
	}
	

	@Override
	public void saveTempPaymentBillTask(PaymentBill paymentBill,UUID uuid) throws RuntimeException, IllegalAccessException, InvocationTargetException {
		if (paymentBill!=null){
			TempPaymentBill tempPaymentBill = new TempPaymentBill();
			BeanUtils.copyProperties(tempPaymentBill,paymentBill);
			tempPaymentBill.setStageStatus(1);
			tempPaymentBill.setUuid(uuid.toString());
			profitDao.saveTempPaymentBill(tempPaymentBill);
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
		profitDao.updateStatus("TempPaymentBill", "stageStatus", "stageStatus", 1, 2,"");
		profitDao.updateStatus("PaymentBill", "divideStatus", "divideStatus", 1, 2,"");
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
		profitDao.updateStatus("TempPaymentBill", "stageStatus", "stageStatus", 2, 3,"");
		profitDao.updateStatus("PaymentBill", "divideStatus", "divideStatus", 2, 3,"");
	}


	@Override
	public void createServiceTransferBill(Long serviceTransferBillId,Double profitMoney, Long outAccountId, Long inAccountId)
			throws RuntimeException {

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
		profitDao.updateStatus("TempPaymentBill", "stageStatus", "stageStatus", 3, 4,"");
		profitDao.updateStatus("PaymentBill", "divideStatus", "divideStatus", 3, 4,"");
	}
	
	@Override
	public void createTransferBill(Long serviceTransferBillId,Double profitMoney, Long outAccountId, Long inAccountId, Integer transferType)
			throws RuntimeException {

		TransferRecords transferRecord = new TransferRecords();
		transferRecord.setId(serviceTransferBillId);
		transferRecord.setPoundage(0);
		transferRecord.setTransferMoney(profitMoney);
		transferRecord.setTransferOutAccountId(outAccountId);
		transferRecord.setTransferInAccountId(inAccountId);
		transferRecord.setTransferTime(new Date());
		transferRecord.setTransferStatus(0);
		transferRecord.setTransferType(transferType);
		profitDao.save(transferRecord);
	}


	@Override
	public void saveServiceTransferRecords(Long serviceBillId,
			Long transferAccountId) throws RuntimeException {
		ServiceTransferRelation relation = new ServiceTransferRelation();
		ServiceTransferRelationId relationId = new ServiceTransferRelationId(serviceBillId,transferAccountId);
		relation.setId(relationId);
		profitDao.save(relation);
		profitDao.updateStatus("TempPaymentBill", "stageStatus", "stageStatus", 4, 5,"");
		profitDao.updateStatus("PaymentBill", "divideStatus", "divideStatus", 4, 5,"");
	}


	@Override
	public void clearTempPaymentBill() throws RuntimeException {
		profitDao.deleteTempPaymentBill();
		profitDao.updateStatus("PaymentBill", "divideStatus", "divideStatus", 5, 6,"");
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
	public void saveServicePoundageRelation(Long serviceBillId, Long poundageBillId)
			throws RuntimeException {
		PoundageServiceRelationId poundageServiceId = new PoundageServiceRelationId();
		poundageServiceId.setPoundageId(poundageBillId);
		poundageServiceId.setServiceBillId(serviceBillId);
		PoundageServiceRelation poundageServiceRelation = new PoundageServiceRelation(poundageServiceId);
		profitDao.save(poundageServiceRelation);
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
	public void savePoundageTransferRecords(Long poundageBillId,
			Long transferAccountId) throws RuntimeException {
		PoundageTransferRelationId relationId = new PoundageTransferRelationId(poundageBillId,transferAccountId);
		PoundageTransferRelation relation = new PoundageTransferRelation(relationId);
		profitDao.save(relation);
	}


	@Override
	public List<PaymentBill> findPaymentBillList(Long serviceBillId) throws RuntimeException {
		
		return profitDao.findPaymentBills(serviceBillId);
	}


	@Override
	public List<PaymentAgentBo> findAgentInfo(Long serviceBillId)
			throws RuntimeException {
		
		return profitDao.findAgentInfo(serviceBillId);
	}


	@Override
	public AgentBo findAgentRule(Long agentId) throws RuntimeException {
		
		return profitDao.findAgentRule(agentId);
	}


	@Override
	public Long saveSettleBill(Long agentId, Double settleBillMoney)
			throws RuntimeException {
		Long settleBillId = 0L;
		Date createTime = new Date();
		String billId = agentId.toString()+(new java.text.SimpleDateFormat("yyyyMMddhhmmss")).format(createTime).toString();
		settleBillId = Long.valueOf(billId);
		SettleBill bill = new SettleBill();
		bill.setAgentId(agentId);
		bill.setId(settleBillId);
		bill.setCreateDate(createTime);
		bill.setConfirmDate(createTime);
		bill.setProfitMoney(settleBillMoney);
		bill.setStatus(2);
		profitDao.save(bill);
		return settleBillId;
	}


	@Override
	public void saveServiceSettleRelation(Long serviceBillId,
			List<PaymentAgentBo> settleBillIdList) throws RuntimeException {
		for(PaymentAgentBo settleBillId:settleBillIdList){
			SettleServiceRelationId relationId = new SettleServiceRelationId();
			relationId.setServiceBillId(serviceBillId);
			relationId.setSettleBillId(settleBillId.getBillId());
			SettleServiceRelation relation = new SettleServiceRelation(relationId);
			profitDao.save(relation);
		}
	}

	@Override
	public void savePoundageBill(Long poundageBillId, Double poundageMoney)
			throws RuntimeException {
		PoundageBill poundageBill = new PoundageBill();
		poundageBill.setId(poundageBillId);
		poundageBill.setProfitMoney(poundageMoney);
		poundageBill.setCreateDate(new Date());
		poundageBill.setConfirmDate(new Date());
		poundageBill.setStatus(2);
		profitDao.save(poundageBill);
	}

	@Override
	public void saveTransferSettle(List<PaymentAgentBo> settleBillIdList)
			throws RuntimeException {
		for (PaymentAgentBo settleBillId:settleBillIdList){
			Long transferBillId = DbidGenerator.getDbidGenerator().getNextId();
			this.createTransferBill(transferBillId, settleBillId.getServiceFee(), this.getSystemAccountId(0), this.getSystemAccountId(5),12);
			SettleTransferRelationId relationId=new SettleTransferRelationId();
			relationId.setReceiptsId(settleBillId.getBillId());
			relationId.setTransferRecordsId(transferBillId);
			SettleTransferRelation relation = new SettleTransferRelation(relationId);
			profitDao.save(relation);
		}
	}


	@Override
	public Double getSystemProfitMoney(Long serviceBillId)
			throws RuntimeException {
		return profitDao.getSystemProfitMoney(serviceBillId);
	}


	@Override
	public Double getSystemSettleMoney(Long serviceBillId)
			throws RuntimeException {
		return profitDao.getSystemSettleMoney(serviceBillId);
	}


	@Override
	public void saveIncomeBill(Long incomeBillId, Double incomeMoney)
			throws RuntimeException {
		IncomeBill incomeBill = new IncomeBill();
		incomeBill.setId(incomeBillId);
		incomeBill.setConfirmDate(new Date());
		incomeBill.setCreateDate(new Date());
		incomeBill.setProfitMoney(incomeMoney);
		incomeBill.setStatus(2);
		profitDao.save(incomeBill);
	}


	@Override
	public void saveServiceIncomeRelation(Long serviceBillId, Long incomeBillId)
			throws RuntimeException {
		IncomeServiceRelationId incomeServiceId = new IncomeServiceRelationId();
		incomeServiceId.setIncomeBillId(incomeBillId);
		incomeServiceId.setServiceBillId(serviceBillId);
		IncomeServiceRelation incomeServiceRelation = new IncomeServiceRelation(incomeServiceId);
		profitDao.save(incomeServiceRelation);
	}


	@Override
	public void saveIncomeTransferRecords(Long incomeBillId,
			Long transferAccountId) throws RuntimeException {
		IncomeTransferRelationId relationId = new IncomeTransferRelationId(incomeBillId,transferAccountId);
		IncomeTransferRelation relation = new IncomeTransferRelation(relationId);
		profitDao.save(relation);
	}


	@Override
	public Long getNextId() throws RuntimeException {
		// TODO Auto-generated method stub
		return  DbidGenerator.getDbidGenerator().getNextId();
	}

}
