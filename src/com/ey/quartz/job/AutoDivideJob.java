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
		//��������ѻ��
		Long serviceBillId = calculateServiceBill(calculateService);
		if (serviceBillId!=null){
			//����ϵͳ�����˻����
			calculateProfitBill(calculateService,serviceBillId);
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
			//����������˻�ת�˵�
			calculateService.createServiceTransferBill(transferBillId,profitMoney);
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
	private void calculateProfitBill(ProfitCalculateService calculateService, 
			Long serviceBillId) throws Exception{
		//�������滮�����
		Long profitBillId = DbidGenerator.getDbidGenerator().getNextId();
		
	}
	
	
}
