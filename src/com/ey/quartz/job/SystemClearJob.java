package com.ey.quartz.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;

import com.ey.service.ProfitCalculateService;
import com.ey.util.DateUtil;

public class SystemClearJob implements Job{

	@Override
	public void execute(JobExecutionContext context) {
		JobDataMap data = context.getJobDetail().getJobDataMap();
		ProfitCalculateService calculateService = (ProfitCalculateService) data.get("calculateService");
		try {
			createClearBill(calculateService);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createClearBill(ProfitCalculateService calculateService) throws Exception{
		String currentDay = DateUtil.convertDateToString("yyyymmdd", new Date());
		int currentMonth = Integer.valueOf(currentDay.substring(5,7));
		int currentYear = Integer.valueOf(currentDay.substring(0,4));
		if (currentMonth==1){
			currentMonth = 12;
			currentYear=currentYear-1;
		}else{
			currentMonth = currentMonth-1;
		}
		calculateService.createSystemClearBill(currentYear,currentMonth);
	}

}
