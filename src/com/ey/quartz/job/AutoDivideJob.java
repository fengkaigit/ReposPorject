package com.ey.quartz.job;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import com.ey.bo.AgentBo;
import com.ey.bo.PaymentAgentBo;
import com.ey.dao.common.dbid.DbidGenerator;
import com.ey.dao.entity.AgentInfo;
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
			//处理系统手续费划款单(包括：用户缴费时手续费手续费)
			Long poundageBillId = calculatePoundagetBill(calculateService,serviceBillId);
			//处理系统收益账户划款单(返回收益划款单号List)
			Long profitBillId = calculateProfitBill(calculateService,serviceBillId);
			//按代理商处理系统结算账户划款单(返回结算划款单号List)
			List<PaymentAgentBo> settleIdList = calculateSettleBill(calculateService,serviceBillId);
			//处理系统最终盈利划款单(收益－代理商结算－手续费)
			Long incomeBillId = calculateIncomeBill(calculateService,serviceBillId);
		}
		
	}
	
	//处理劳务费划款单
	private Long calculateServiceBill(ProfitCalculateService calculateService) throws RuntimeException, IllegalAccessException, InvocationTargetException{
		List<PaymentBill> billList=null;
		//生成劳务费划款单单号
		Long serviceBillId = calculateService.getNextId();
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
			Long transferBillId = calculateService.getNextId();
			
			//获取系统大账户ID 获取转出账户Id
			Long systemAccountId = calculateService.getSystemAccountId(0);
			if (systemAccountId==null)
				throw new RuntimeException("未设置系统大账户"); 
			//获取系统劳务费账户ID　获取转入账户Id
			Long serviceAccountId = calculateService.getSystemAccountId(4);
			if (serviceAccountId==null)
				throw new RuntimeException("未设置系统劳务费账户");
			
			//生成劳务费账户转账单
			calculateService.createServiceTransferBill(transferBillId,profitMoney,systemAccountId,serviceAccountId);
			//保存劳务费划款单与转账单关系表
			calculateService.saveServiceTransferRecords(serviceBillId,transferBillId);
			//清空临时缴费单表
			calculateService.clearTempPaymentBill();
			return serviceBillId;
		}
		return null;
	}
	
	protected void processTask(ProfitCalculateService calculateService,
			PaymentBill paymentBill,UUID uuid, Long serviceBillId) throws RuntimeException, IllegalAccessException, InvocationTargetException{
		calculateService.updatePaymentBillUUID(uuid);
		calculateService.saveTempPaymentBillTask(paymentBill,uuid);
	}
	
	private Long calculatePoundagetBill(ProfitCalculateService calculateService, 
			Long serviceBillId) throws RuntimeException{
		//获取系统大账户ID 获取转出账户Id
		Long systemAccountId = calculateService.getSystemAccountId(0);
		if (systemAccountId==null)
			throw new RuntimeException("未设置系统大账户"); 
		//获取系统手续费账户ID　获取转入账户Id
		Long profitAccountId = calculateService.getSystemAccountId(3);
		if (profitAccountId==null)
			throw new RuntimeException("未设置系统手续账户");
		//生成手续费划款单单号
		Long poundageBillId = calculateService.getNextId();
		//查询符合划款的所有缴费单明细对应转账记录的手续费合计金额
		Double poundage=calculateService.findTransferRecordsPoundage(serviceBillId);
		//生成系统手续费划款单
		calculateService.savePoundageBill(poundageBillId,poundage);
		//保存手续费账户划款单与劳务费划款单关系表
		calculateService.saveServicePoundageRelation(serviceBillId,poundageBillId);
		//生成手续费账户转账单单号
		Long transferBillId = calculateService.getNextId();
		//生成手续费账户转账单
		calculateService.createTransferBill(transferBillId,poundage,systemAccountId,profitAccountId,10);
		//保存手续费账户转账关系表
		calculateService.savePoundageTransferRecords(poundageBillId,transferBillId);
		return poundageBillId;
	}
	
	//处理系统收益账户划款单
	private Long calculateProfitBill(ProfitCalculateService calculateService, 
			Long serviceBillId) throws RuntimeException{
		//获取系统大账户ID 获取转出账户Id
		Long systemAccountId = calculateService.getSystemAccountId(0);
		if (systemAccountId==null)
			throw new RuntimeException("未设置系统大账户"); 
		//获取系统收益账户ID　获取转入账户Id
		Long profitAccountId = calculateService.getSystemAccountId(1);
		if (profitAccountId==null)
			throw new RuntimeException("未设置系统收益账户");
		
		//生成收益划款单单号
		Long profitBillId = calculateService.getNextId();
		//查询符合划款的所有缴费单明细对应转账记录的手续费合计金额
		Double poundage=calculateService.findTransferRecordsPoundage(serviceBillId);
		//查询劳务费划款单的金额
		Double serviceMoney=calculateService.findServiceBillMoney(serviceBillId);
		//收益账户划款单金额:劳务费划款单金额-对应缴费记录的手续费
		Double profitMoney = serviceMoney-poundage;
		//生成收益账户划款单
		calculateService.saveProfitBill(profitBillId,profitMoney);
		//保存收益账户划款单与劳务费划款单关系表
		calculateService.saveServiceProfitRelation(serviceBillId,profitBillId);
		//生成收益账户转账单单号
		Long transferBillId = calculateService.getNextId();
		//生成收益账户转账单
		calculateService.createTransferBill(transferBillId,profitMoney,systemAccountId,profitAccountId,6);
		//保存收益账户转账关系表
		calculateService.saveProfitTransferRecords(profitBillId,transferBillId);
		return profitBillId;
	}
	
	//按代理商处理系统结算账户划款单(返回结算单位划款单号List)
	private List<PaymentAgentBo> calculateSettleBill(ProfitCalculateService calculateService, 
			Long serviceBillId) throws RuntimeException, ClassNotFoundException, 
			NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException{
		//获取系统大账户ID 获取转出账户Id
		Long systemAccountId = calculateService.getSystemAccountId(0);
		if (systemAccountId==null)
			throw new RuntimeException("未设置系统大账户"); 
		//获取系统结算账户ID　获取转入账户Id
		Long profitAccountId = calculateService.getSystemAccountId(5);
		if (profitAccountId==null)
			throw new RuntimeException("未设置系统结算账户");
		//通过缴费单查找区域信息及对应区域代理商信息
		List<PaymentAgentBo> agentList = calculateService.findAgentInfo(serviceBillId);
		//通过代理商信息查找代理商返规则，计算代理商返点金额，生成结算单
		for (PaymentAgentBo agentInfo:agentList){
			//查询代理商返点规则
			AgentBo agent = calculateService.findAgentRule(agentInfo.getId());
			String className="";
			String funName="";
			//计算代理商返点金额
			String[] ruleArray = agent.getRule().split(";");
			for (String rule:ruleArray){
				if (rule.contains("1")){
					String[] strRule = rule.split(":");
					className=strRule[1];
				}
			}
			//定义反射类
			Class<?> calculate = Class.forName(className);
			Method func=calculate.getDeclaredMethod("calculateAgentRebackDot", Double.class, Double.class);
			Double settleMoney = (Double)func.invoke(calculate.newInstance(), agentInfo.getServiceFee(), agent.getRebackDot());
			//生成代理商结算划款单（单号生成规则：代理商ID+生成日期,返回结算单号）
			Long settleBillId=calculateService.saveSettleBill(agentInfo.getId(),settleMoney);
			agentInfo.setBillId(settleBillId);
			agentInfo.setServiceFee(settleMoney);
			agentInfo.setAccountId(agent.getAgentAccount());
		}
		//保存代理商结算划款单与劳务费划款单关系表
		calculateService.saveServiceSettleRelation(serviceBillId,agentList);
		//生成代理商结算转账单并保存代理商结算划款单与转账单关系
		calculateService.saveTransferSettle(agentList);
		return agentList;
	}
	
	//处理系统最终盈利
	private Long calculateIncomeBill(ProfitCalculateService calculateService,
			Long serviceBillId) throws RuntimeException{
		//获取系统大账户ID 获取转出账户Id
		Long systemAccountId = calculateService.getSystemAccountId(0);
		if (systemAccountId==null)
			throw new RuntimeException("未设置系统大账户"); 
		//获取系统最终盈利账户ID　获取转入账户Id
		Long profitAccountId = calculateService.getSystemAccountId(2);
		if (profitAccountId==null)
			throw new RuntimeException("未设置系统最终盈利账户");
		//生成系统最终盈利账户划款单单号
		Long incomeBillId =calculateService.getNextId();
		//获取系统收益金额
		Double profitMoney = calculateService.getSystemProfitMoney(serviceBillId);
		//获取系统结算金额
		Double poundageMoney = calculateService.getSystemSettleMoney(serviceBillId);
		//获取系统最终盈利金额(=系统收益金额－系统结算金额)
		Double incomeMoney = profitMoney - poundageMoney;
		//生成系统最终盈利账户划款单
		calculateService.saveIncomeBill(incomeBillId, incomeMoney);
		//保存最终盈利账户划款单与劳务费划款单关系
		calculateService.saveServiceIncomeRelation(serviceBillId, incomeBillId);
		Long transferBillId = calculateService.getNextId();
		//生成最终盈利账户转账记录
		calculateService.createTransferBill(transferBillId,incomeMoney,systemAccountId,profitAccountId,8);
		//保存转账关系
		calculateService.saveIncomeTransferRecords(incomeBillId,transferBillId);
		return incomeBillId;
	}
}
