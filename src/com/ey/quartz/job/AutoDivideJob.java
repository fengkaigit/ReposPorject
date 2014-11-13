package com.ey.quartz.job;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ey.dao.entity.PaymentBill;
import com.ey.service.ProfitCalculateService;

public class AutoDivideJob implements Job{

	@Override
	public void execute(JobExecutionContext context){
		JobDataMap data = context.getJobDetail().getJobDataMap();
		ProfitCalculateService calculateService = (ProfitCalculateService) data.get("ProfitCalculateService");
		try {
			profitCalculate(calculateService);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void profitCalculate(ProfitCalculateService calculateService) throws Exception {
		calculateRemuneration(calculateService);
	}
	
	private void calculateRemuneration(ProfitCalculateService calculateService) throws Exception{
		List<PaymentBill> billList = calculateService.findPaymentBillList();
	}
	
}
