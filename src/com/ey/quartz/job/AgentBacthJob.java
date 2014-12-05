package com.ey.quartz.job;

import java.util.ArrayList;
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
			Date date = new Date();
			String currentDay = DateUtil.getDate(date);
			Map<Integer,String> map = getPayTypeName(data);
			List<Object[]> billlist = agentService.findBillByCurrentDay(currentDay);
			List<BatchPaymentRelation> batchpaylist = new ArrayList<BatchPaymentRelation>();
			if (billlist != null && billlist.size() > 0) {
				for (Object[] o : billlist) {
					AgentPaymentBatch paymentBatch = new AgentPaymentBatch(
							(Double) o[3], date, 0, (Long) o[1],
							(Integer) o[0], (Long) o[4],map.get((Integer)o[0]));
					agentService.savePaymentBatch(paymentBatch);
					String[] billIds = ((String) o[2])
							.split(SystemConst.SPLITE_SIGN_COMMON);
					for (String billId : billIds) {
						batchpaylist.add(new BatchPaymentRelation(
								new BatchPaymentRelationId(Long.valueOf(billId
										.trim()), paymentBatch.getId())));
					}
				}
				agentService.batchSaveObject(batchpaylist);
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
