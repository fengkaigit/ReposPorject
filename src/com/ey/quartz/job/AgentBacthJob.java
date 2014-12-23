package com.ey.quartz.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ey.consts.SystemConst;
import com.ey.dao.entity.AgentPaymentBatch;
import com.ey.dao.entity.BaseCustomValue;
import com.ey.dao.entity.BatchPaymentRelation;
import com.ey.dao.entity.BatchPaymentRelationId;
import com.ey.service.AgentService;
import com.ey.service.ProfitCalculateService;
import com.ey.service.StaticService;
import com.ey.util.DateUtil;

public class AgentBacthJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobDataMap data = arg0.getJobDetail().getJobDataMap();
		AgentService agentService = (AgentService) data.get("agentService");
		if(agentService!=null){
			try{
				Map<Integer,String> map = getPayTypeName(data);
				agentService.createAgentBatch(map);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	private Map<Integer,String> getPayTypeName(JobDataMap jobDataMap){
		Map<Integer,String> dataValueMap = new HashMap<Integer,String>();
		StaticService staticService = (StaticService) jobDataMap.get("staticService");
		if(staticService!=null){
		   List<BaseCustomValue> customValues = staticService.listValues("payment_type");
		   if(customValues!=null&&customValues.size()>0){
              for(BaseCustomValue value:customValues){
        	     dataValueMap.put(value.getId().getDataValue(), value.getPropChName());
              }
		   }
		}
        return dataValueMap;
	}

}
