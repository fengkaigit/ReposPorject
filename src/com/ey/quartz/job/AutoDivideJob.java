package com.ey.quartz.job;

import java.util.List;
import java.util.UUID;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import com.ey.bo.AgentBo;
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
		//��������ѻ��
		Long serviceBillId = calculateServiceBill(calculateService);
		if (serviceBillId!=null){
			//����ϵͳ�����˻����
			Long profitBillId = calculateProfitBill(calculateService,serviceBillId);
			//�������̴���ϵͳ�����˻����(���ؽ��㵥λ�����List)
			List<Long> settleIdList = calculateSettleBill(calculateService,serviceBillId);
		}
	}
	
	//��������ѻ��
	private Long calculateServiceBill(ProfitCalculateService calculateService) throws Exception{
		List<PaymentBill> billList=null;
		//��������ѻ������
		Long serviceBillId = DbidGenerator.getDbidGenerator().getNextId();
		//�޸Ľɷѵ���ϸ״̬�������ɷ�״̬Ϊ����ɹ�����δ���ɹ�����ѻ���ĽɷѼ�¼��
		Integer intStatus= calculateService.changePaymentBillStatus();
		if (intStatus>0){
			//��ѯ���ϻ�������нɷѵ���ϸ���ɷ�״̬Ϊ����ɹ�����δ���ɹ�����ѻ���ĽɷѼ�¼��
			billList=calculateService.findPaymentBillList(10,1);
		}
		if(null != billList && billList.size() > 0){
			UUID uuid = UUID.randomUUID();
			for(PaymentBill paymentBill:billList){
				//���ɷ���ϸ������ʱ�ɷѵ���
				processTask(calculateService,paymentBill,uuid,serviceBillId);
			}
			//��������ѻ��
			Double profitMoney = calculateService.createServiceChargeBill(serviceBillId);
			//��������ѻ����ɷѵ���ϵ��
			calculateService.saveServicePaymentRelation(serviceBillId,billList);
			//����������˻�ת�˵�����
			Long transferBillId = DbidGenerator.getDbidGenerator().getNextId();
			
			//��ȡϵͳ���˻�ID ��ȡת���˻�Id
			Long systemAccountId = calculateService.getSystemAccountId(0);
			if (systemAccountId==null)
				throw new Exception("δ����ϵͳ���˻�"); 
			//��ȡϵͳ������˻�ID����ȡת���˻�Id
			Long serviceAccountId = calculateService.getSystemAccountId(4);
			if (serviceAccountId==null)
				throw new Exception("δ����ϵͳ������˻�");
			
			//����������˻�ת�˵�
			calculateService.createTransferBill(transferBillId,profitMoney,systemAccountId,serviceAccountId);
			//��������ѻ����ת�˵���ϵ��
			calculateService.saveServiceTransferRecords(serviceBillId,transferBillId);
			//�����ʱ�ɷѵ���
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
	
	//����ϵͳ�����˻����
	private Long calculateProfitBill(ProfitCalculateService calculateService, 
			Long serviceBillId) throws Exception{
		//��ȡϵͳ���˻�ID ��ȡת���˻�Id
		Long systemAccountId = calculateService.getSystemAccountId(0);
		if (systemAccountId==null)
			throw new Exception("δ����ϵͳ���˻�"); 
		//��ȡϵͳ������˻�ID����ȡת���˻�Id
		Long profitAccountId = calculateService.getSystemAccountId(1);
		if (profitAccountId==null)
			throw new Exception("δ����ϵͳ�����˻�");
		
		//�������滮�����
		Long profitBillId = DbidGenerator.getDbidGenerator().getNextId();
		//��ѯ���ϻ�������нɷѵ���ϸ��Ӧת�˼�¼�������Ѻϼƽ��
		Double poundage=calculateService.findTransferRecordsPoundage(serviceBillId);
		//��ѯ����ѻ���Ľ��
		Double serviceMoney=calculateService.findServiceBillMoney(serviceBillId);
		//�����˻�������:����ѻ�����-��Ӧ�ɷѼ�¼��������
		Double profitMoney = serviceMoney-poundage;
		//���������˻����
		calculateService.saveProfitBill(profitBillId,profitMoney);
		//���������˻����������ѻ����ϵ��
		calculateService.saveServiceProfitRelation(serviceBillId,profitBillId);
		//���������˻�ת�˵�����
		Long transferBillId = DbidGenerator.getDbidGenerator().getNextId();
		//���������˻�ת�˵�
		calculateService.createTransferBill(transferBillId,profitMoney,systemAccountId,profitAccountId);
		//���������˻�ת�˹�ϵ��
		calculateService.saveProfitTransferRecords(profitBillId,transferBillId);
		return profitBillId;
	}
	
	//�������̴���ϵͳ�����˻����(���ؽ��㵥λ�����List)
	private List<Long> calculateSettleBill(ProfitCalculateService calculateService, 
			Long serviceBillId) throws Exception{
		//ͨ���ɷѵ�����������Ϣ����Ӧ�����������Ϣ
		List<AgentInfo> agentList = calculateService.findAgentInfo(serviceBillId);
		//ͨ����������Ϣ���Ҵ����̷����򣬼�������̷�������ɽ��㵥
		for (AgentInfo agentInfo:agentList){
			//��ѯ�����̷������
			AgentBo agent = calculateService.findAgentRule(agentInfo.getId());
			//��������̷�����
			//Double rebackMoney = 
		}
		return null;
	}
	
	
}
