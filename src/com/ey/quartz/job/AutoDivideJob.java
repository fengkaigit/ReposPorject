package com.ey.quartz.job;

import java.util.List;
import java.util.UUID;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import com.ey.dao.common.dbid.DbidGenerator;
import com.ey.dao.entity.PaymentBill;
import com.ey.service.ProfitCalculateService;

public class AutoDivideJob implements Job{

	@Override
	public void execute(JobExecutionContext context){
		JobDataMap data = context.getJobDetail().getJobDataMap();
		ProfitCalculateService calculateService = (ProfitCalculateService) data.get("calculateService");
		try {
			profitCalculate(calculateService);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void profitCalculate(ProfitCalculateService calculateService) throws Exception {
		//处理劳务费划款单
		Long serviceBillId = calculateServiceBill(calculateService);
		if (serviceBillId!=null){
			//处理系统收益账户划款单
			calculateProfitBill(calculateService,serviceBillId);
		}
	}
	
	//处理劳务费划款单
	private Long calculateServiceBill(ProfitCalculateService calculateService) throws Exception{
		List<PaymentBill> billList=null;
		//生成劳务费划款单单号
		Long serviceBillId = DbidGenerator.getDbidGenerator().getNextId();
		//修改缴费单明细状态（锁定缴费状态为办理成功，且未生成过劳务费划款单的缴费记录）
		Integer intStatus= calculateService.changePaymentBillStatus();
		if (intStatus>0){
			//查询符合划款的所有缴费单明细（缴费状态为办理成功，且未生成过劳务费划款单的缴费记录）
			billList=calculateService.findPaymentBillList(10,1);
		}
		if(null != billList && billList.size() > 0){
			UUID uuid = UUID.randomUUID();
			for(PaymentBill paymentBill:billList){
				//将缴费明细插入临时缴费单表
				processTask(calculateService,paymentBill,uuid,serviceBillId);
			}
			//生成劳务费划款单
			Double profitMoney = calculateService.createServiceChargeBill(serviceBillId);
			//保存劳务费划款单与缴费单关系表
			calculateService.saveServicePaymentRelation(serviceBillId,billList);
			//生成劳务费账户转账单单号
			Long transferBillId = DbidGenerator.getDbidGenerator().getNextId();
			//生成劳务费账户转账单
			calculateService.createServiceTransferBill(transferBillId,profitMoney);
			//保存劳务费划款单与转账单关系表
			calculateService.saveServiceTransferRecords(serviceBillId,transferBillId);
			//清空临时缴费单表
			calculateService.clearTempPaymentBill();
			return serviceBillId;
		}
		return null;
	}
	
	protected void processTask(ProfitCalculateService calculateService,
			PaymentBill paymentBill,UUID uuid, Long serviceBillId) throws Exception{
		calculateService.updatePaymentBillUUID(uuid);
		calculateService.saveTempPaymentBillTask(paymentBill,uuid);
	}
	
	//处理系统收益账户划款单
	private void calculateProfitBill(ProfitCalculateService calculateService, 
			Long serviceBillId) throws Exception{
		//生成收益划款单单号
		Long profitBillId = DbidGenerator.getDbidGenerator().getNextId();
		
	}
	
	
}
