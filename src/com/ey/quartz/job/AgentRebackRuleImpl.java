package com.ey.quartz.job;

public class AgentRebackRuleImpl implements AgentRebackRule {

	@Override
	public  Double calculateAgentRebackDot(Double serviceMoney,Double dot) throws Exception {
		Double reBackDouble = serviceMoney * dot;
		return reBackDouble;
	}

	@Override
	public Double calculateAgentRebackDot() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
